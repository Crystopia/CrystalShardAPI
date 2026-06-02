package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.kyori.adventure.text.Component

fun ClientPacketFactory.openScreen(
    id: Int,
    title: Component,
    type: MenuType,
    callback: (packet: ClientPacket<ClientboundOpenScreenPacketData>) -> Unit
): ClientPacket<ClientboundOpenScreenPacketData> {

    val data = ClientboundOpenScreenPacketData(
        id, type, PaperAdventure.asVanilla(title)
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.openScreenPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.openScreenPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.openScreenPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.openScreenPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundOpenScreenPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}