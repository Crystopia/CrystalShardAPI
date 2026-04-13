package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.createOpenSignEditor(
    blockPos: BlockPos,
    isFrontText: Boolean,
    callback: (packet: Shard_Packet<ClientboundOpenSignEditorPacketData>) -> Unit
): Shard_Packet<ClientboundOpenSignEditorPacketData> {

    val data = ClientboundOpenSignEditorPacketData(
        blockPos, isFrontText
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.openSignEditorPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.openSignEditorPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.openSignEditorPacket(
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