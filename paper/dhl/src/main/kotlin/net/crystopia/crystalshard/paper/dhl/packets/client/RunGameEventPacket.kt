package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundGameEventPacketData
import net.crystopia.crystalshard.dhl.shared.enums.game.GameEventType
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder

fun ClientPacketFactory.runGameEvent(
    type: GameEventType,
    /**
     * More about action: https://minecraft.wiki/w/Java_Edition_protocol/Packets#Game_Event
     */
    action: Float,
    callback: (packet: ClientPacket<ClientboundGameEventPacketData>) -> Unit
): ClientPacket<ClientboundGameEventPacketData> {

    val data = ClientboundGameEventPacketData(
        type, action
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.runGameEvent(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.runGameEvent(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundGameEventPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}