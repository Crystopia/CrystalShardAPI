package net.crystopia.crystalshard.paper.dhl.extension

import net.crystopia.crystalshard.dhl.shared.ClientPacket
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

fun Player.removeServerPacketListener(key: String) {
    val serverPlayer = (player as CraftPlayer).handle
    val channel = serverPlayer.connection.connection.channel

    channel.pipeline().remove(key)
}
fun Player.removeServerPacketListener(key: NamespacedKey) {
    val serverPlayer = (player as CraftPlayer).handle
    val channel = serverPlayer.connection.connection.channel

    channel.pipeline().remove("${key.namespace}_${key.key}")
}

fun Player.hasServerPacketListener(key: String) : Boolean {
    val serverPlayer = (player as CraftPlayer).handle
    val channel = serverPlayer.connection.connection.channel

    return channel.pipeline().get(key) != null
}

fun <T : Any> ClientPacket<T>.send(players: MutableList<Player>): ClientPacket<T> {
    players.forEach { player ->
        val serverPlayer = (player as CraftPlayer).handle


        val world = Bukkit.getWorld("world") as CraftWorld

        world.handle.dimensionType()


        val connection = serverPlayer.connection
        connection.send(packetObject)
    }
    return this
}