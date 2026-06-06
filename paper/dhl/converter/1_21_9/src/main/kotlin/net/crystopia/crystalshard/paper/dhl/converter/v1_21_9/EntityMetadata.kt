package net.crystopia.crystalshard.paper.dhl.converter.v1_21_9

import com.sun.jdi.InvalidTypeException
import io.papermc.paper.adventure.PaperAdventure
import io.papermc.paper.world.WeatheringCopperState
import net.crystopia.crystalshard.dhl.shared.builder.EntityMetadataBuilder
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityRotation
import net.crystopia.crystalshard.dhl.shared.data.merchant.VillagerData
import net.crystopia.crystalshard.dhl.shared.data.variant.PaintigVariant
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3i
import net.crystopia.crystalshard.dhl.shared.enums.entities.ArmadilloState
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.merchant.build
import net.kyori.adventure.text.Component
import net.minecraft.core.*
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityReference
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.animal.CatVariant
import net.minecraft.world.entity.animal.ChickenVariant
import net.minecraft.world.entity.animal.CowVariant
import net.minecraft.world.entity.animal.PigVariant
import net.minecraft.world.entity.animal.armadillo.Armadillo
import net.minecraft.world.entity.animal.coppergolem.CopperGolemState
import net.minecraft.world.entity.animal.frog.FrogVariant
import net.minecraft.world.entity.animal.sniffer.Sniffer
import net.minecraft.world.entity.animal.wolf.WolfSoundVariant
import net.minecraft.world.entity.animal.wolf.WolfVariant
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.variant.ModelAndTexture
import net.minecraft.world.entity.variant.SpawnPrioritySelectors
import net.minecraft.world.item.component.ResolvableProfile
import net.minecraft.world.level.block.WeatheringCopper
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.craftbukkit.CraftParticle
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.*

@Suppress("UNCHECKED_CAST")
fun EntityMetadataBuilder.PAPER_1_21_9(data: EntityMetadata<*>): SynchedEntityData.DataValue<*> {
    when (data.type) {
        EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION -> {

            val accessor = Player.DATA_PLAYER_MODE_CUSTOMISATION
            return SynchedEntityData.DataValue.create(accessor, data.value as Byte)
        }

        EntityDataSerializerType.BYTE -> {

            val serializer = EntityDataSerializers.BYTE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as Byte)
        }

        EntityDataSerializerType.INT -> {

            val serializer = EntityDataSerializers.INT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as Int)
        }

        EntityDataSerializerType.LONG -> {

            val serializer = EntityDataSerializers.LONG
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as Long)
        }

        EntityDataSerializerType.FLOAT -> {

            val serializer = EntityDataSerializers.FLOAT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as Float)
        }

        EntityDataSerializerType.STRING -> {

            val serializer = EntityDataSerializers.STRING
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as String)
        }

        EntityDataSerializerType.COMPONENT, EntityDataSerializerType.OPTIONAL_COMPONENT -> {

            val serializer = EntityDataSerializers.COMPONENT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, PaperAdventure.asVanilla(data.value as Component))
        }

        EntityDataSerializerType.ITEM_STACK -> {

            val serializer = EntityDataSerializers.ITEM_STACK
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, CraftItemStack.asNMSCopy(data.value as ItemStack))
        }

        EntityDataSerializerType.BLOCK_STATE, EntityDataSerializerType.OPTIONAL_BLOCK_STATE -> {

            val serializer = EntityDataSerializers.BLOCK_STATE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                CraftBlockType.bukkitToMinecraft(data.value as Material).defaultBlockState()
            )
        }

        EntityDataSerializerType.BOOLEAN -> {
            val serializer = EntityDataSerializers.BOOLEAN
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, data.value as Boolean)
        }

        EntityDataSerializerType.PARTICLE, EntityDataSerializerType.PARTICLES -> {
            val serializer = EntityDataSerializers.PARTICLE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                CraftParticle.createParticleParam((data.value as Particle), data.value as Particle)
            )
        }

        EntityDataSerializerType.ROTATIONS -> {

            if (data.value !is EntityRotation) {
                throw InvalidTypeException("Use net.crystopia.crystalshard.dhl.shared.data.entities.EntityRotation")
            }
            val rotation = data.value as EntityRotation

            val serializer = EntityDataSerializers.ROTATIONS
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Rotations(rotation.x, rotation.y, rotation.z)
            )
        }

        EntityDataSerializerType.BLOCK_POS,
        EntityDataSerializerType.OPTIONAL_BLOCK_POS -> {

            if (data.value !is net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos) {
                throw InvalidTypeException("Use net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos")
            }

            val serializer = EntityDataSerializers.BLOCK_POS
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                BlockPos(
                    (data.value as net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos).x,
                    (data.value as net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos).y,
                    (data.value as net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos).z,
                )
            )
        }

        EntityDataSerializerType.DIRECTION -> {

            val serializer = EntityDataSerializers.DIRECTION
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Direction.valueOf((data.value as net.crystopia.crystalshard.dhl.shared.enums.server.Direction).toString())
            )
        }

        EntityDataSerializerType.OPTIONAL_LIVING_ENTITY_REFERENCE -> {
            val serializer = EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Optional.of(EntityReference.of(data.value as UUID))
            )
        }

        EntityDataSerializerType.OPTIONAL_GLOBAL_POS -> {
            val location = data.value as Location

            val serializer = EntityDataSerializers.OPTIONAL_GLOBAL_POS
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Optional.of(
                    GlobalPos(
                        (location.world as CraftWorld).handle.dimension(),
                        BlockPos(
                            location.x.toInt(), location.y.toInt(), location.z.toInt()
                        )
                    )
                )
            )
        }

        EntityDataSerializerType.VILLAGER_DATA -> {
            val serializer = EntityDataSerializers.VILLAGER_DATA
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                (data.value as VillagerData).build()
            )
        }

        EntityDataSerializerType.OPTIONAL_UNSIGNED_INT -> {
            val serializer = EntityDataSerializers.OPTIONAL_UNSIGNED_INT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                OptionalInt.of(data.value as Int)
            )
        }

        EntityDataSerializerType.POSE -> {
            val serializer = EntityDataSerializers.POSE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Pose.valueOf((data.value as Pose).toString())
            )
        }

        EntityDataSerializerType.CAT_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.CatVariant
            val serializer = EntityDataSerializers.CAT_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    CatVariant(
                        ClientAsset.ResourceTexture(
                            ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.CHICKEN_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.ChickenVariant
            val model = when (variant.type) {
                net.crystopia.crystalshard.dhl.shared.enums.entities.ChickenVariant.WARM -> {
                    ChickenVariant.ModelType.NORMAL
                }

                net.crystopia.crystalshard.dhl.shared.enums.entities.ChickenVariant.COLD -> {
                    ChickenVariant.ModelType.COLD
                }

                net.crystopia.crystalshard.dhl.shared.enums.entities.ChickenVariant.TEMPERATE -> {
                    ChickenVariant.ModelType.NORMAL
                }

            }

            val serializer = EntityDataSerializers.CHICKEN_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    ChickenVariant(
                        ModelAndTexture<ChickenVariant.ModelType>(
                            model,
                            ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                            )
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.COW_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.CowVariant
            val model = when (variant.type) {
                net.crystopia.crystalshard.dhl.shared.enums.entities.CowVariant.COLD -> {
                    CowVariant.ModelType.COLD
                }

                net.crystopia.crystalshard.dhl.shared.enums.entities.CowVariant.WARM -> {
                    CowVariant.ModelType.WARM
                }

                net.crystopia.crystalshard.dhl.shared.enums.entities.CowVariant.NORMAL -> {
                    CowVariant.ModelType.NORMAL
                }

            }

            val serializer = EntityDataSerializers.COW_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    CowVariant(
                        ModelAndTexture(
                            model,
                            ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.WOLF_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.WolfVariant
            val serializer = EntityDataSerializers.WOLF_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    WolfVariant(
                        WolfVariant.AssetInfo(
                            ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                            ), ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                            ), ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                            )
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.WOLF_SOUND_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.WolfSoundVariant
            val serializer = EntityDataSerializers.WOLF_SOUND_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    WolfSoundVariant(
                        Holder.direct(
                            variant.ambientSound
                        ), Holder.direct(
                            variant.deathSound
                        ), Holder.direct(
                            variant.growlSound
                        ), Holder.direct(
                            variant.hurtSound
                        ), Holder.direct(
                            variant.pantSound
                        ), Holder.direct(
                            variant.whineSound
                        )
                    )
                )
            )
        }

        EntityDataSerializerType.FROG_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.FrogVariant
            val serializer = EntityDataSerializers.FROG_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    FrogVariant(
                        ClientAsset.ResourceTexture(
                            ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.PIG_VARIANT -> {
            val variant = data.value as net.crystopia.crystalshard.dhl.shared.data.variant.PigVariant
            val serializer = EntityDataSerializers.PIG_VARIANT
            val accessor = serializer.createAccessor(data.index)

            val model = when (variant.type) {
                net.crystopia.crystalshard.dhl.shared.enums.entities.PigVariant.COLD -> {
                    PigVariant.ModelType.COLD
                }

                net.crystopia.crystalshard.dhl.shared.enums.entities.PigVariant.NORMAL -> {
                    PigVariant.ModelType.NORMAL
                }
            }

            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    PigVariant(
                        ModelAndTexture(
                            model,
                            ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                            )
                        ),
                        SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                    )
                )
            )
        }

        EntityDataSerializerType.PAINTING_VARIANT -> {
            val variant = data.value as PaintigVariant
            val serializer = EntityDataSerializers.PAINTING_VARIANT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Holder.direct(
                    PaintingVariant(
                        variant.width, variant.height,
                        ResourceLocation.tryBuild(variant.assetId.namespace, variant.assetId.key)!!,
                        Optional.ofNullable(variant.title),
                        Optional.ofNullable(variant.author)
                    )
                )
            )
        }

        EntityDataSerializerType.ARMADILLO_STATE -> {
            val state = when (data.value as ArmadilloState) {
                ArmadilloState.IDLE -> {
                    Armadillo.ArmadilloState.IDLE
                }

                ArmadilloState.ROLLING -> {
                    Armadillo.ArmadilloState.ROLLING
                }

                ArmadilloState.SCARED -> {
                    Armadillo.ArmadilloState.SCARED
                }

                ArmadilloState.UNROLLING -> {
                    Armadillo.ArmadilloState.UNROLLING
                }
            }


            val serializer = EntityDataSerializers.ARMADILLO_STATE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                state
            )
        }

        EntityDataSerializerType.SNIFFER_STATE -> {
            val state = when (data.value as org.bukkit.entity.Sniffer.State) {
                org.bukkit.entity.Sniffer.State.FEELING_HAPPY -> {
                    Sniffer.State.FEELING_HAPPY
                }

                org.bukkit.entity.Sniffer.State.SCENTING -> {
                    Sniffer.State.SCENTING
                }

                org.bukkit.entity.Sniffer.State.SNIFFING -> {
                    Sniffer.State.SNIFFING
                }

                org.bukkit.entity.Sniffer.State.SEARCHING -> {
                    Sniffer.State.SEARCHING
                }

                org.bukkit.entity.Sniffer.State.RISING -> {
                    Sniffer.State.RISING
                }

                org.bukkit.entity.Sniffer.State.DIGGING -> {
                    Sniffer.State.DIGGING
                }

                org.bukkit.entity.Sniffer.State.IDLING -> {
                    Sniffer.State.IDLING
                }
            }


            val serializer = EntityDataSerializers.SNIFFER_STATE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                state
            )
        }

        EntityDataSerializerType.WEATHERING_COPPER_STATE -> {
            val state = when (data.value as WeatheringCopperState) {
                WeatheringCopperState.WEATHERED -> {
                    WeatheringCopper.WeatherState.WEATHERED
                }

                WeatheringCopperState.OXIDIZED -> {
                    WeatheringCopper.WeatherState.OXIDIZED
                }

                WeatheringCopperState.UNAFFECTED -> {
                    WeatheringCopper.WeatherState.UNAFFECTED
                }

                WeatheringCopperState.EXPOSED -> {
                    WeatheringCopper.WeatherState.EXPOSED
                }
            }


            val serializer = EntityDataSerializers.WEATHERING_COPPER_STATE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                state
            )
        }

        EntityDataSerializerType.COPPER_GOLEM_STATE -> {
            val state =
                when (data.value as net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState) {
                    net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState.IDLE -> {
                        CopperGolemState.IDLE
                    }

                    net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState.GETTING_ITEM -> {
                        CopperGolemState.GETTING_ITEM
                    }

                    net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState.DROPPING_ITEM -> {
                        CopperGolemState.DROPPING_ITEM
                    }

                    net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState.DROPPING_NO_ITEM -> {
                        CopperGolemState.DROPPING_NO_ITEM
                    }

                    net.crystopia.crystalshard.dhl.shared.enums.entities.CopperGolemState.GETTING_NO_ITEM -> {
                        CopperGolemState.GETTING_NO_ITEM
                    }
                }


            val serializer = EntityDataSerializers.COPPER_GOLEM_STATE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                state
            )
        }

        EntityDataSerializerType.VECTOR3 -> {
            val vec = data.value as Vec3i
            val serializer = EntityDataSerializers.VECTOR3
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Vector3f(vec.x.toFloat(), vec.y.toFloat(), vec.z.toFloat())
            )
        }


        EntityDataSerializerType.QUATERNION -> {
            val vec = data.value as net.crystopia.crystalshard.dhl.shared.data.world.Quaternionf


            val serializer = EntityDataSerializers.QUATERNION
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                Quaternionf(
                    vec.x, vec.y, vec.z, vec.w
                )
            )
        }

        EntityDataSerializerType.RESOLVABLE_PROFILE -> {
            val profile = when (data.value) {
                is UUID, String -> {
                    when (data.value) {
                        is String -> {
                            ResolvableProfile.createUnresolved(data.value as String)
                        }

                        is UUID -> {
                            ResolvableProfile.createUnresolved(data.value as UUID)
                        }

                        else -> {
                            throw NotImplementedError("No Implementation for this Type")
                        }
                    }
                }

                is org.bukkit.entity.Player -> {
                    val serverPlayer = (data.value as CraftPlayer).handle
                    ResolvableProfile.createResolved(serverPlayer.gameProfile)
                }

                else -> throw NotImplementedError("No Implementation for this Type")
            }


            val serializer = EntityDataSerializers.RESOLVABLE_PROFILE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(
                accessor,
                profile
            )
        }

        EntityDataSerializerType.HUMANOID_ARM -> {
            throw ClassNotFoundException("HUMANOID_ARM is not implemented in 1.21.10")
        }

    }
}
