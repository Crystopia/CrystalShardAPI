package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.server.Shard_ServerboundCustomClickActionPacket

fun ServerPacketFactory.customClickActionEvent(
    data: Shard_ServerPacketData, callback: CustomClickEvent.() -> Unit
) {
    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            Shard_ServerboundCustomClickActionPacket().attach(data, callback)
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundCustomClickActionPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.server.Shard_ServerboundCustomClickActionPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_1 -> {
            throw Exception("CustomClickAction Events are not available in 1.21.1")
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
}