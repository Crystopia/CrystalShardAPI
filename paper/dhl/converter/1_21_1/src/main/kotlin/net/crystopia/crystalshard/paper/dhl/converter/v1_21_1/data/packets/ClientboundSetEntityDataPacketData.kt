package net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets

import com.sun.jdi.InvalidTypeException
import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityMetadata
import net.crystopia.crystalshard.dhl.shared.data.entities.EntityRotation
import net.crystopia.crystalshard.dhl.shared.data.merchant.VillagerData
import net.crystopia.crystalshard.dhl.shared.data.variant.PaintigVariant
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3i
import net.crystopia.crystalshard.dhl.shared.enums.entities.ArmadilloState
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityDataSerializerType
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.merchant.build
import net.kyori.adventure.text.Component
import net.minecraft.core.*
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.animal.CatVariant
import net.minecraft.world.entity.animal.armadillo.Armadillo
import net.minecraft.world.entity.animal.sniffer.Sniffer
import net.minecraft.world.entity.decoration.PaintingVariant
import net.minecraft.world.entity.player.Player
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.craftbukkit.CraftParticle
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import org.joml.Quaternionf
import org.joml.Vector3f
import java.util.*

@Suppress("UNCHECKED_CAST")
fun Entity.v1_21_1_MetaData(data: EntityMetadata<*>): SynchedEntityData.DataValue<*> {
    when (data.type) {
        EntityDataSerializerType.DATA_PLAYER_MODE_CUSTOMISATION -> {

            val accessor = Player.DATA_PLAYER_MODE_CUSTOMISATION
            return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
        }

        EntityDataSerializerType.BYTE -> {

            val serializer = EntityDataSerializers.BYTE
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, (data.value as Byte))
        }

        EntityDataSerializerType.INT -> {

            val serializer = EntityDataSerializers.INT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, (data.value as Int))
        }

        EntityDataSerializerType.LONG -> {

            val serializer = EntityDataSerializers.LONG
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, (data.value as Long))
        }

        EntityDataSerializerType.FLOAT -> {

            val serializer = EntityDataSerializers.FLOAT
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, (data.value as Float))
        }

        EntityDataSerializerType.STRING -> {

            val serializer = EntityDataSerializers.STRING
            val accessor = serializer.createAccessor(data.index)
            return SynchedEntityData.DataValue.create(accessor, (data.value as String))
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
            throw ClassNotFoundException("OPTIONAL_LIVING_ENTITY_REFERENCE is not implemented in 1.21.1")
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
            val location = data.value as Location

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
                        ResourceLocation.tryBuild(variant.type.namespace, variant.type.key)!!
                    )
                )
            )
        }

        EntityDataSerializerType.CHICKEN_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
        }

        EntityDataSerializerType.COW_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
        }

        EntityDataSerializerType.WOLF_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
        }

        EntityDataSerializerType.WOLF_SOUND_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
        }

        EntityDataSerializerType.FROG_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
        }

        EntityDataSerializerType.PIG_VARIANT -> {
            throw ClassNotFoundException("CHICKEN_VARIANT is not implemented in 1.21.1")
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
            throw ClassNotFoundException("WEATHERING_COPPER_STATE is not implemented in 1.21.1")
        }

        EntityDataSerializerType.COPPER_GOLEM_STATE -> {
            throw ClassNotFoundException("COPPER_GOLEM_STATE is not implemented in 1.21.1")
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
            throw ClassNotFoundException("COPPER_GOLEM_STATE is not implemented in 1.21.1")
        }

        EntityDataSerializerType.HUMANOID_ARM -> {
            throw ClassNotFoundException("HUMANOID_ARM is not implemented in 1.21.1")
        }

    }
}