package net.crystopia.crystalshard.paper.dhl.extension

import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

fun Player.removeServerPacketListener(key: String) {
    val serverPlayer = (player as CraftPlayer).handle
    val channel = serverPlayer.connection.connection.channel

    channel.pipeline().remove(key)
}