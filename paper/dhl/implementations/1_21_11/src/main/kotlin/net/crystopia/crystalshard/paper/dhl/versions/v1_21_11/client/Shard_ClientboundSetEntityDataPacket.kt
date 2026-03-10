package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client


import com.sun.jdi.InvalidTypeException
import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityRotation
import net.crystopia.crystalshard.paper.dhl.shared.data.merchant.VillagerData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.converter.data.merchant.build
import net.kyori.adventure.text.Component
import net.minecraft.core.*
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.Identifier
import net.minecraft.world.entity.EntityReference
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.animal.chicken.ChickenVariant
import net.minecraft.world.entity.animal.cow.CowVariant
import net.minecraft.world.entity.animal.feline.CatVariant
import net.minecraft.world.entity.animal.wolf.WolfSoundVariant
import net.minecraft.world.entity.animal.wolf.WolfVariant
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.variant.ModelAndTexture
import net.minecraft.world.entity.variant.SpawnPrioritySelectors
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.craftbukkit.CraftParticle
import org.bukkit.craftbukkit.CraftSound
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Chicken
import org.bukkit.entity.Cow
import org.bukkit.inventory.ItemStack
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
                    )
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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.COMPONENT, EntityDataSerializerType.OPTIONAL_COMPONENT -> {

                val serializer = EntityDataSerializers.COMPONENT
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, PaperAdventure.asVanilla(data.value as Component))
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.ITEM_STACK -> {

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

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.BOOLEAN -> {
                val serializer = EntityDataSerializers.BOOLEAN
                val accessor = serializer.createAccessor(data.index)
                return SynchedEntityData.DataValue.create(accessor, data.value as Boolean)
            }

            net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType.PARTICLE, EntityDataSerializerType.PARTICLES -> {
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
                                Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
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
                                    Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
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
                                Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
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
                                    Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                ), ClientAsset.ResourceTexture(
                                    Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
                                ), ClientAsset.ResourceTexture(
                                    Identifier.tryBuild(variant.type.key.namespace, variant.type.key.key)!!
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
            // TODO: FROG_VARIANT, PIG_VARIANT, PAINTING_VARIANT, ARMADILLO_STATE, SNIFFER_STATE, WEATHERING_COPPER_STATE, COPPER_GOLEM_STATE, VECTOR3, QUATERNION, RESOLVABLE_PROFILE

            else -> {
                TODO("Report this error to the CrystalShard Team! (CODE: NO_ENTITY_DATA_TYPE)")
            }
        }
    }
}