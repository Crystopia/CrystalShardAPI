package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTakeItemEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.takeItem(
    itemId: Int,
    playerId: Int,
    amount: Int,
    callback: (packet: Shard_Packet<ClientboundTakeItemEntityPacketData>) -> Unit
): Shard_Packet<ClientboundTakeItemEntityPacketData> {

    val data = ClientboundTakeItemEntityPacketData(
        itemId, playerId, amount
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.takeItem(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.takeItem(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.takeItem(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.takeItem(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundTakeItemEntityPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}