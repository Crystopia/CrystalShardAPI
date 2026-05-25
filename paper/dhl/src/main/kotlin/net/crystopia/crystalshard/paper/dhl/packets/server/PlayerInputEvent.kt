package net.crystopia.crystalshard.paper.dhl.packets.server

import net.crystopia.crystalshard.dhl.ServerPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.server.PlayerInputEvent
import net.crystopia.crystalshard.dhl.shared.data.packets.server.Shard_ServerPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.server.Shard_ServerboundPlayerInputPacket
import org.bukkit.craftbukkit.entity.CraftPlayer

fun ServerPacketFactory.playerInputEvent(
    player: org.bukkit.entity.Player,
    name: org.bukkit.NamespacedKey,
    shouldPublish: Boolean,
    callback: PlayerInputEvent.() -> Unit
) {
    val data = Shard_ServerPacketData(
        (player as CraftPlayer).handle,
        NamespacedKey(name.namespace, name.key),
        shouldPublish
    )
    when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            Shard_ServerboundPlayerInputPacket().attach(data, callback)
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.server.Shard_ServerboundPlayerInputPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.server.Shard_ServerboundPlayerInputPacket()
                .attach(data, callback)
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.server.Shard_ServerboundPlayerInputPacket()
                .attach(data, callback)
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }
}