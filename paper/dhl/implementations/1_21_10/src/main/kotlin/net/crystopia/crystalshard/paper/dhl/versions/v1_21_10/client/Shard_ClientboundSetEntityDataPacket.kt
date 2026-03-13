package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import com.sun.jdi.InvalidTypeException
import io.papermc.paper.adventure.PaperAdventure
import io.papermc.paper.world.WeatheringCopperState
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityRotation
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.ArmadilloState
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.merchant.build
import net.kyori.adventure.text.Component
import net.minecraft.core.*
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
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
import org.bukkit.craftbukkit.CraftSound
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Chicken
import org.bukkit.entity.Cow
import org.bukkit.entity.Pig
import org.bukkit.inventory.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.*

class Shard_ClientboundSetEntityDataPacket : IPacket<ClientboundSetEntityDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEntityDataPacketData
    ): ClientboundSetEntityDataPacket {
        val list = mutableListOf<SynchedEntityData.DataValue<*>>()
        packetObj.entityData.forEach { (index, type, value) ->
            list.add(
                buildMetaData(
                    EntityMetadata(
                        index = index,
                        type = type,
                        value = value
                    ) as EntityMetadata<*>
                )
            )
        }
        return ClientboundSetEntityDataPacket(packetObj.entityId, list)
    }

    @Suppress("UNCHECKED_CAST")
    fun buildMetaData(data: EntityMetadata<*>): SynchedEntityData.DataValue<*> {
        when (data.type) {
            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION -> {

                val accessor = Player.DATA_PLAYER_MODE_CUSTOMISATION
                return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BYTE -> {

                val serializer = EntityDataSerializers.BYTE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.INT -> {

                val serializer = EntityDataSerializers.INT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Int))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.LONG -> {

                val serializer = EntityDataSerializers.LONG
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Long))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.FLOAT -> {

                val serializer = EntityDataSerializers.FLOAT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as Float))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.STRING -> {

                val serializer = EntityDataSerializers.STRING
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, (data.value as String))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.COMPONENT, net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_COMPONENT -> {

                val serializer = EntityDataSerializers.COMPONENT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, PaperAdventure.asVanilla(data.value as Component))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.ITEM_STACK -> {

                val serializer = EntityDataSerializers.ITEM_STACK
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, CraftItemStack.asNMSCopy(data.value as ItemStack))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BLOCK_STATE, net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_BLOCK_STATE -> {

                val serializer = EntityDataSerializers.BLOCK_STATE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    CraftBlockType.bukkitToMinecraft(data.value as Material).defaultBlockState()
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BOOLEAN -> {
                val serializer = EntityDataSerializers.BOOLEAN
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, data.value as Boolean)
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PARTICLE, net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PARTICLES -> {
                val serializer = EntityDataSerializers.PARTICLE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    CraftParticle.createParticleParam((data.value as Particle), data.value as Particle)
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.ROTATIONS -> {

                if (data.value !is EntityRotation) {
                    throw InvalidTypeException("Use net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityRotation")
                }
                val rotation = data.value as EntityRotation

                val serializer = EntityDataSerializers.ROTATIONS
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Rotations(rotation.x, rotation.y, rotation.z)
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BLOCK_POS,
            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_BLOCK_POS -> {

                if (data.value !is net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos) {
                    throw InvalidTypeException("Use net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos")
                }

                val serializer = EntityDataSerializers.BLOCK_POS
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    BlockPos(
                        (data.value as net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos).x,
                        (data.value as net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos).y,
                        (data.value as net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos).z,
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.DIRECTION -> {

                val serializer = EntityDataSerializers.DIRECTION
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Direction.valueOf((data.value as net.crystopia.crystalshard.paper.dhl.shared.enums.server.Direction).toString())
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_LIVING_ENTITY_REFERENCE -> {
                val serializer = EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    java.util.Optional.of(EntityReference.of(data.value as UUID))
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_GLOBAL_POS -> {
                val location = data.value as Location

                val serializer = EntityDataSerializers.OPTIONAL_GLOBAL_POS
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    java.util.Optional.of(
                        GlobalPos(
                            (location.world as CraftWorld).handle.dimension(),
                            BlockPos(
                                location.x.toInt(), location.y.toInt(), location.z.toInt()
                            )
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.VILLAGER_DATA -> {
                val location = data.value as Location

                val serializer = EntityDataSerializers.VILLAGER_DATA
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    (data.value as VillagerData).build()
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.OPTIONAL_UNSIGNED_INT -> {
                val serializer = EntityDataSerializers.OPTIONAL_UNSIGNED_INT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    OptionalInt.of(data.value as Int)
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.POSE -> {
                val serializer = EntityDataSerializers.POSE
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Pose.valueOf((data.value as Pose).toString())
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.CAT_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.CatVariant
                val serializer = EntityDataSerializers.CAT_VARIANT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        CatVariant(
                            ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.CHICKEN_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.ChickenVariant
                val model = when (variant.type) {
                    Chicken.Variant.COLD -> {
                        ChickenVariant.ModelType.COLD
                    }

                    Chicken.Variant.WARM -> {
                        ChickenVariant.ModelType.NORMAL
                    }

                    Chicken.Variant.TEMPERATE -> {
                        ChickenVariant.ModelType.NORMAL
                    }

                    else -> {
                        throw IllegalArgumentException("No more variants...")
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
                                    ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                )
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.COW_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.CowVariant
                val model = when (variant.type) {
                    Cow.Variant.COLD -> {
                        CowVariant.ModelType.COLD
                    }

                    Cow.Variant.WARM -> {
                        CowVariant.ModelType.WARM
                    }

                    Cow.Variant.TEMPERATE -> {
                        CowVariant.ModelType.NORMAL
                    }

                    else -> {
                        throw IllegalArgumentException("No more variants...")
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
                                ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.WOLF_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.WolfVariant
                val serializer = EntityDataSerializers.WOLF_VARIANT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        WolfVariant(
                            WolfVariant.AssetInfo(
                                ClientAsset.ResourceTexture(
                                    ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                ), ClientAsset.ResourceTexture(
                                    ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                ), ClientAsset.ResourceTexture(
                                    ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                )
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.WOLF_SOUND_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.WolfSoundVariant
                val serializer = EntityDataSerializers.WOLF_SOUND_VARIANT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        WolfSoundVariant(
                            Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.ambientSound)
                            ), Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.deathSound)
                            ), Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.growlSound)
                            ), Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.hurtSound)
                            ), Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.pantSound)
                            ), Holder.direct(
                                CraftSound.bukkitToMinecraft(variant.whineSound)
                            )
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.FROG_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.FrogVariant
                val serializer = EntityDataSerializers.FROG_VARIANT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        FrogVariant(
                            net.minecraft.core.ClientAsset.ResourceTexture(
                                ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PIG_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.PigVariant
                val serializer = EntityDataSerializers.PIG_VARIANT
                val accessor = serializer.createAccessor(data.index)

                val model = when (variant.type) {
                    Pig.Variant.COLD -> {
                        PigVariant.ModelType.COLD
                    }

                    Pig.Variant.WARM -> {
                        PigVariant.ModelType.NORMAL
                    }

                    Pig.Variant.TEMPERATE -> {
                        PigVariant.ModelType.NORMAL
                    }

                    else -> {
                        throw IllegalArgumentException("No more variants...")
                    }
                }

                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        PigVariant(
                            ModelAndTexture(
                                model,
                                ClientAsset.ResourceTexture(
                                    ResourceLocation.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                )
                            ),
                            SpawnPrioritySelectors.fallback(variant.spawnPrioritySelectors)
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PAINTING_VARIANT -> {
                val variant = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.variant.PaintigVariant
                val serializer = EntityDataSerializers.PAINTING_VARIANT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Holder.direct(
                        PaintingVariant(
                            variant.width, variant.height,
                            ResourceLocation.tryBuild(variant.assetId.namespace, variant.assetId.key)!!,
                            Optional.ofNullable(PaperAdventure.asVanilla(variant.title)),
                            Optional.ofNullable(PaperAdventure.asVanilla(variant.author))
                        )
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.ARMADILLO_STATE -> {
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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.SNIFFER_STATE -> {
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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.WEATHERING_COPPER_STATE -> {
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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.COPPER_GOLEM_STATE -> {
                val state =
                    when (data.value as net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState) {
                        net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState.IDLE -> {
                            CopperGolemState.IDLE
                        }

                        net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState.GETTING_ITEM -> {
                            CopperGolemState.GETTING_ITEM
                        }

                        net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState.DROPPING_ITEM -> {
                            CopperGolemState.DROPPING_ITEM
                        }

                        net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState.DROPPING_NO_ITEM -> {
                            CopperGolemState.DROPPING_NO_ITEM
                        }

                        net.crystopia.crystalshard.paper.dhl.shared.enums.entities.CopperGolemState.GETTING_NO_ITEM -> {
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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.VECTOR3 -> {
                val vec = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3i
                val serializer = EntityDataSerializers.VECTOR3
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Vector3f(vec.x.toFloat(), vec.y.toFloat(), vec.z.toFloat())
                )
            }


            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.QUATERNION -> {
                val vec = data.value as net.crystopia.crystalshard.paper.dhl.shared.data.world.Quaternionf


                val serializer = EntityDataSerializers.QUATERNION
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(
                    accessor,
                    Quaternionf(
                        vec.x, vec.y, vec.z, vec.w
                    )
                )
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.RESOLVABLE_PROFILE -> {
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
}