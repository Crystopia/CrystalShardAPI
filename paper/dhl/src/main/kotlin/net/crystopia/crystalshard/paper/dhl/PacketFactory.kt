package net.crystopia.crystalshard.paper.dhl

import com.mojang.authlib.GameProfile
import com.mojang.datafixers.util.Pair
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.Attribute
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAnimatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockDestructionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundContainerSetSlotPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerInfoRemovePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundRemoveEntitiesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEquipmentPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundUpdateAttributesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder
import net.kyori.adventure.text.Component
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import org.bukkit.Location
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.EnumSet
import java.util.UUID

object PacketFactory {

    fun setContainerSlot(
        id: Int,
        revision: Int,
        slot: Int,
        item: ItemStack,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundContainerSetSlotPacketData(
            id, revision, slot, item
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerSlot(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerSlot(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun closeContainerPacket(
        id: Int,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundContainerClosePacketData(
            id
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.closeContainerPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.closeContainerPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun openScreenPacket(
        id: Int,
        title: Component,
        type: MenuType<*>,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundOpenScreenPacketData(
            id, type, title
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.openScreenPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.openScreenPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun updateAttributesPacket(
        entityId: Int,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        attributes: MutableList<Attribute>,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundUpdateAttributesPacketData(
            entityId, attributes
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.updateAttributesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.updateAttributesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun sendEntityEventPacket(
        entity: Entity,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        status: Byte,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundEntityEventPacketData(
            entity, status
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.entityEventPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.entityEventPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createAnimatePacket(
        entity: Entity,
        animationId: Int,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundAnimatePacketData(
            entity, animationId
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.animatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.animatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createBlockDestroyStagePacket(
        entityId: Int, pos: BlockPos,
        /**
         * Read more: [Packets#Block_Entity_Data](https://minecraft.wiki/w/Java_Edition_protocol/Packets#Block_Entity_Data)
         */
        progress: Int,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundBlockDestructionPacketData(
            entityId, pos, progress
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setBlockDestroyStagePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setBlockDestroyStagePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createOpenSignEditorPacket(
        blockPos: BlockPos,
        isFrontText: Boolean,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundOpenSignEditorPacketData(
            blockPos, isFrontText
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.openSignEditorPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.openSignEditorPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createBlockEntityDataPacket(
        blockPos: BlockPos,
        type: BlockEntityType<*>,
        nbt: CompoundTag,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundBlockEntityDataPacketData(
            blockPos, type, nbt
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.blockEntityDataPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.blockEntityDataPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createBlockUpdatePacket(
        pos: BlockPos, state: BlockState, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundBlockUpdatePacketData(
            pos, state
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.blockUpdatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.blockUpdatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        callback(packet)
        return packet
    }

    fun createEquipmentPacket(
        entityId: Int, equipmentList: MutableList<Pair<EquipmentSlot, net.minecraft.world.item.ItemStack>>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundSetEquipmentPacketData(
            entityId, equipmentList
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.equipmentPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.equipmentPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun playerInfoUpdatePacket(
        serverPlayer: ServerPlayer,
        gameProfile: GameProfile,
        actions: EnumSet<InfoUpdateAction>,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundPlayerInfoUpdatePacketData(
            serverPlayer, gameProfile, actions
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playerInfoUpdatePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun teleportEntityPacket(
        entityId: Int, location: Location, onGround: Boolean, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundTeleportEntityPacketData(
            entityId, location, onGround
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.teleportEntityPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun playerInfoRemovePacket(
        uuids: MutableList<UUID>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundPlayerInfoRemovePacketData(
            uuids
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.playerInfoRemovePacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playerInfoRemovePacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun removeEntitiesPacket(
        entityIds: List<Int>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundRemoveEntitiesPacketData(
            entityIds
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.removeEntitiesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.removeEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun addEntitiesPacket(
        entityId: Int,
        entityUUID: UUID,
        location: Location,
        entityType: EntityType<*>,
        data: Int,
        deltaMovement: Vec3 = Vec3.ZERO,
        yHeadRot: Double = 0.0,
        callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundAddEntityPacketData(
            entityId, entityUUID, location, entityType, data, deltaMovement, yHeadRot
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.addEntitiesPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun rotateHeadPacket(
        entity: Entity, yaw: Float, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundRotateHeadPacketData(
            entity, yaw
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.rotateHeadPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     *
     * ```
     * // Use the 6 for the Index.
     * val accessor = EntityDataAccessor<Pose>(6, EntityDataSerializers.POSE)
     *                 PacketFactory.setEntityDataPacket(
     *                     playerEntity.id, mutableListOf(
     *                         SynchedEntityData.DataValue.create(
     *                             accessor,
     *                             Pose.SLEEPING
     *                         )
     *                     )
     *                 ) { packet ->
     *                     PacketFactory.sendPacket(packet, CrystalShard.plugin.server.onlinePlayers.toMutableList())
     *                 }
     * ```
     */

    fun setEntityDataPacket(
        entityId: Int, entityData: MutableList<SynchedEntityData.DataValue<*>>, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundSetEntityDataPacketData(
            entityId, entityData
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setEntityDataPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setEntityDataPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun setPassengersPacket(
        entity: Entity, callback: (packet: Packet<*>) -> Unit
    ): Packet<*> {

        val data = ClientboundSetPassengersPacketData(
            entity
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setPassengersPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setPassengersPacket(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }


        callback(packet)
        return packet
    }

    fun sendPacket(packet: Packet<*>, players: MutableList<Player>) {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }

}