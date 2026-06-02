package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundShowDialogPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.types.dialog.Dialog
import net.crystopia.crystalshard.paper.dhl.types.dialog.toDhl
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil

fun ClientPacketFactory.showDialog(
    dialog: Dialog<*>,
    callback: (packet: ClientPacket<ClientboundShowDialogPacketData>) -> Unit
): ClientPacket<ClientboundShowDialogPacketData> {
    val data = ClientboundShowDialogPacketData(
        dialog.toDhl()
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.showDialog(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.showDialog(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.showDialog(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.showDialog(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundShowDialogPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}