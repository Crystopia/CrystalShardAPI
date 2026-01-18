package net.crystopia.crystalshard.paper.dhl

import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.*
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockType
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityMetadata
import net.crystopia.crystalshard.paper.dhl.shared.data.packetsid.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets.Shard_ClientboundRemoveEntitiesPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder
import net.kyori.adventure.text.Component
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.Packet
import net.minecraft.server.packs.repository.Pack
import org.bukkit.Location
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

object PacketFactory {

    fun sendWaypoint(
        operation: WaypointOperation,
        waypoints: TrackedWaypoint<*>,
        callback: (packet: Shard_Packet<ClientboundTrackedWaypointPacketData>) -> Unit
    ): Shard_Packet<ClientboundTrackedWaypointPacketData> {

        val data = ClientboundTrackedWaypointPacketData(
            operation, waypoints
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.sendWaypointPacket(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                throw Exception("Waypoint Packets are not implemented in this Version!")
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundTrackedWaypointPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerData(
        id: Int,
        property: Short,
        value: Short,
        callback: (packet: Shard_Packet<ClientboundContainerSetDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetDataPacketData> {

        val data = ClientboundContainerSetDataPacketData(
            id, property, value
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerData(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerData(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerSetDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerContent(
        id: Int,
        stateId: Int,
        items: MutableList<ItemStack>,
        carriedItem: ItemStack,
        callback: (packet: Shard_Packet<ClientboundContainerSetContentPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetContentPacketData> {

        val data = ClientboundContainerSetContentPacketData(
            id, stateId, items, carriedItem
        )

        val packet = when (ServerUtil.currentVersion()) {
            ServerVersion.v1_21_10 -> {
                PacketBuilder.setContainerContent(
                    data
                )
            }

            ServerVersion.v1_21_1 -> {
                net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setContainerContent(
                    data
                )
            }

            else -> {
                throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
            }
        }

        val shardPacket = Shard_Packet<ClientboundContainerSetContentPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun setContainerSlot(
        id: Int,
        revision: Int,
        slot: Int,
        item: ItemStack,
        callback: (packet: Shard_Packet<ClientboundContainerSetSlotPacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerSetSlotPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundContainerSetSlotPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun closeContainerPacket(
        id: Int,
        callback: (packet: Shard_Packet<ClientboundContainerClosePacketData>) -> Unit
    ): Shard_Packet<ClientboundContainerClosePacketData> {

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

        val shardPacket = Shard_Packet<ClientboundContainerClosePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun openScreenPacket(
        id: Int,
        title: Component,
        type: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.MenuType,
        callback: (packet: Shard_Packet<ClientboundOpenScreenPacketData>) -> Unit
    ): Shard_Packet<ClientboundOpenScreenPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundOpenScreenPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun updateAttributesPacket(
        entityId: Int,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        attributes: MutableList<Attribute>,
        callback: (packet: Shard_Packet<ClientboundUpdateAttributesPacketData>) -> Unit
    ): Shard_Packet<ClientboundUpdateAttributesPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundUpdateAttributesPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun sendEntityEventPacket(
        entityId: Int,
        /**
         * See more infos about status. [Entity_statuses](https://minecraft.wiki/w/Java_Edition_protocol/Entity_statuses)
         */
        status: Byte,
        callback: (packet: Shard_Packet<ClientboundEntityEventPacketData>) -> Unit
    ): Shard_Packet<ClientboundEntityEventPacketData> {

        val data = ClientboundEntityEventPacketData(
            entityId, status
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

        val shardPacket = Shard_Packet<ClientboundEntityEventPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createAnimatePacket(
        entityId: Int,
        animationId: Int,
        callback: (packet: Shard_Packet<ClientboundAnimatePacketData>) -> Unit
    ): Shard_Packet<ClientboundAnimatePacketData> {

        val data = ClientboundAnimatePacketData(
            entityId, animationId
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

        val shardPacket = Shard_Packet<ClientboundAnimatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockDestroyStagePacket(
        entityId: Int, pos: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos,
        /**
         * Read more: [Packets#Block_Entity_Data](https://minecraft.wiki/w/Java_Edition_protocol/Packets#Block_Entity_Data)
         */
        progress: Int,
        callback: (packet: Shard_Packet<ClientboundBlockDestructionPacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockDestructionPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundBlockDestructionPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createOpenSignEditorPacket(
        blockPos: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos,
        isFrontText: Boolean,
        callback: (packet: Shard_Packet<ClientboundOpenSignEditorPacketData>) -> Unit
    ): Shard_Packet<ClientboundOpenSignEditorPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundOpenSignEditorPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockEntityDataPacket(
        blockPos: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos,
        type: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockEntityType,
        nbt: CompoundTag,
        callback: (packet: Shard_Packet<ClientboundBlockEntityDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockEntityDataPacketData> {

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

        val shardPacket = Shard_Packet<ClientboundBlockEntityDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createBlockUpdatePacket(
        pos: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos,
        state: BlockType,
        callback: (packet: Shard_Packet<ClientboundBlockUpdatePacketData>) -> Unit
    ): Shard_Packet<ClientboundBlockUpdatePacketData> {

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

        val shardPacket = Shard_Packet<ClientboundBlockUpdatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun createEquipmentPacket(
        entityId: Int,
        equipmentList: MutableList<kotlin.Pair<net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EquipmentSlot, ItemStack>>,
        callback: (packet: Shard_Packet<ClientboundSetEquipmentPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetEquipmentPacketData> {

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


        val shardPacket = Shard_Packet<ClientboundSetEquipmentPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun playerInfoUpdatePacket(
        serverPlayer: Player,
        actions: EnumSet<InfoUpdateAction>,
        callback: (packet: Shard_Packet<ClientboundPlayerInfoUpdatePacketData>) -> Unit
    ): Shard_Packet<ClientboundPlayerInfoUpdatePacketData> {

        val data = ClientboundPlayerInfoUpdatePacketData(
            serverPlayer, actions
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


        val shardPacket = Shard_Packet<ClientboundPlayerInfoUpdatePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun teleportEntityPacket(
        entityId: Int, location: Location, onGround: Boolean, callback: (packet: Shard_Packet<ClientboundTeleportEntityPacketData>) -> Unit
    ): Shard_Packet<ClientboundTeleportEntityPacketData> {

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


        val shardPacket = Shard_Packet<ClientboundTeleportEntityPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun playerInfoRemovePacket(
        uuids: MutableList<UUID>, callback: (packet: Shard_Packet<ClientboundPlayerInfoRemovePacketData>) -> Unit
    ): Shard_Packet<ClientboundPlayerInfoRemovePacketData> {

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


        val shardPacket = Shard_Packet<ClientboundPlayerInfoRemovePacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun removeEntitiesPacket(
        entityIds: List<Int>, callback: (packet: Shard_Packet<ClientboundRemoveEntitiesPacketData>) -> Unit
    ): Shard_Packet<ClientboundRemoveEntitiesPacketData> {

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


        val shardPacket = Shard_Packet<ClientboundRemoveEntitiesPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun addEntitiesPacket(
        entityId: Int,
        entityUUID: UUID,
        location: Location,
        entityType: net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.EntityType,
        data: Int,
        yHeadRot: Double = 0.0,
        callback: (packet: Shard_Packet<ClientboundAddEntityPacketData>) -> Unit
    ): Shard_Packet<ClientboundAddEntityPacketData> {

        val data = ClientboundAddEntityPacketData(
            entityId, entityUUID, location, entityType, data, yHeadRot
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

        val shardPacket = Shard_Packet<ClientboundAddEntityPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    fun rotateHeadPacket(
        entityId: Int, yaw: Float, callback: (packet: Shard_Packet<ClientboundRotateHeadPacketData>) -> Unit
    ): Shard_Packet<ClientboundRotateHeadPacketData> {

        val data = ClientboundRotateHeadPacketData(
            entityId, yaw
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

        val shardPacket = Shard_Packet<ClientboundRotateHeadPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    /**
     * Read more about [Entity_Metadata_Format](https://minecraft.wiki/w/Java_Edition_protocol/Entity_metadata#Entity_Metadata_Format)
     *
     */
    fun setEntityDataPacket(
        entityId: Int, entityData: MutableList<EntityMetadata<*>>, callback: (packet:  Shard_Packet<ClientboundSetEntityDataPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetEntityDataPacketData> {

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


        val shardPacket = Shard_Packet<ClientboundSetEntityDataPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet

        callback(shardPacket)
        return shardPacket
    }

    fun setPassengersPacket(
        entity: Entity, passengers: MutableList<Entity>, callback: (packet: Shard_Packet<ClientboundSetPassengersPacketData>) -> Unit
    ): Shard_Packet<ClientboundSetPassengersPacketData> {

        val data = ClientboundSetPassengersPacketData(
            entity, passengers
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

        val shardPacket = Shard_Packet<ClientboundSetPassengersPacketData>()
        shardPacket.packetData = data
        shardPacket.packetObject = packet
        callback(shardPacket)
        return shardPacket
    }

    /**
     *
     */
    fun sendPacket(packet: Packet<*>, players: MutableList<Player>) {
        players.forEach { player ->
            val serverPlayer = (player as CraftPlayer).handle
            serverPlayer.connection.send(packet)
        }
    }

}