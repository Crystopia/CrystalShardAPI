package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRemoveEntitiesPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.removeEntities(
    entityIds: List<Int>, callback: (packet: Shard_Packet<ClientboundRemoveEntitiesPacketData>) -> Unit
): Shard_Packet<ClientboundRemoveEntitiesPacketData> {

    val data = ClientboundRemoveEntitiesPacketData(
        entityIds
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.removeEntitiesPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.removeEntitiesPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.removeEntitiesPacket(
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