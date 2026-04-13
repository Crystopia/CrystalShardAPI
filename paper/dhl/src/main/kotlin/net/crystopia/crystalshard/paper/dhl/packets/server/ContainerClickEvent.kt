package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.ServerPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.gui.Slot
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.ContainerClickEvent
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.server.Shard_ServerboundContainerClickPacket

fun ServerPacketFactory.containerClickEvent(
    items: MutableList<Slot>, data: Shard_ServerPacketData, callback: ContainerClickEvent.() -> Unit
) {
    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            Shard_ServerboundContainerClickPacket(items).attach(data, callback)
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.server.Shard_ServerboundContainerClickPacket(items)
                .attach(data, callback)
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.server.Shard_ServerboundContainerClickPacket(items)
                .attach(data, callback)
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.server.Shard_ServerboundContainerClickPacket(items)
                .attach(data, callback)
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
}