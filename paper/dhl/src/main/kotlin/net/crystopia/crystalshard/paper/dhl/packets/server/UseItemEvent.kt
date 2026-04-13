package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.UseItemEvent
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.server.Shard_ServerboundUseItemPacket

fun ServerPacketFactory.useItemEvent(
    data: Shard_ServerPacketData, callback: UseItemEvent.() -> Unit
) {
    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            Shard_ServerboundUseItemPacket().attach(data, callback)
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundUseItemPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.server.Shard_ServerboundUseItemPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundUseItemPacket()
                .attach(data, callback)
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
}