package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

data class Shard_ServerPacketData(
    var player: Player,
    var name: NamespacedKey,
    var plugin : JavaPlugin
)