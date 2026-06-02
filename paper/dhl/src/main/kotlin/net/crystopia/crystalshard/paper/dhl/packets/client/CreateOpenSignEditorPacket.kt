package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder

fun ClientPacketFactory.createOpenSignEditor(
    blockPos: BlockPos,
    isFrontText: Boolean,
    callback: (packet: ClientPacket<ClientboundOpenSignEditorPacketData>) -> Unit
): ClientPacket<ClientboundOpenSignEditorPacketData> {

    val data = ClientboundOpenSignEditorPacketData(
        blockPos, isFrontText
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.openSignEditorPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.openSignEditorPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.openSignEditorPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.openSignEditorPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundOpenSignEditorPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}