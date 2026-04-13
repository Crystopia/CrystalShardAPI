package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundEntityPositionSyncPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.updateEntityPositionSync(
    entityId: Int,
    values: PositionMoveRotation,
    onGround: Boolean,
    callback: (packet: Shard_Packet<ClientboundEntityPositionSyncPacketData>) -> Unit
): Shard_Packet<ClientboundEntityPositionSyncPacketData> {

    val data = ClientboundEntityPositionSyncPacketData(
        entityId, values, onGround
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.updateEntityPositionSync(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.updateEntityPositionSync(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.updateEntityPositionSync(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundEntityPositionSyncPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}