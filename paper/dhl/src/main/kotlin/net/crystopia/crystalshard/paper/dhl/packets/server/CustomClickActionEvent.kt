package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.server.CustomClickEvent
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.server.Shard_ServerboundCustomClickActionPacket
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.entity.CraftPlayer

fun ServerPacketFactory.customClickActionEvent(
    player: org.bukkit.entity.Player,
    name: org.bukkit.NamespacedKey,
    shouldPublish: Boolean,
    callback: CustomClickEvent.() -> Unit
) {
    val data = Shard_ServerPacketData(
        (player as CraftPlayer).handle,
        NamespacedKey(name.namespace, name.key),
        shouldPublish
    )
    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            Shard_ServerboundCustomClickActionPacket().attach(data, callback)
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.server.Shard_ServerboundCustomClickActionPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.server.Shard_ServerboundCustomClickActionPacket()
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