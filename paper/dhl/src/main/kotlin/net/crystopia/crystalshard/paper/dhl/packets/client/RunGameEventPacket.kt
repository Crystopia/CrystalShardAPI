package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.game.GameEventType
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.runGameEvent(
    type: GameEventType,
    /**
     * More about action: https://minecraft.wiki/w/Java_Edition_protocol/Packets#Game_Event
     */
    action: Float,
    callback: (packet: Shard_Packet<ClientboundGameEventPacketData>) -> Unit
): Shard_Packet<ClientboundGameEventPacketData> {

    val data = ClientboundGameEventPacketData(
        type, action
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.runGameEvent(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundGameEventPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}