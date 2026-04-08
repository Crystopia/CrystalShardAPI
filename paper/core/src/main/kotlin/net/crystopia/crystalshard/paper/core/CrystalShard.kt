package net.crystopia.crystalshard.paper.core

import org.bukkit.plugin.java.JavaPlugin

object CrystalShard {
    var plugin: JavaPlugin? = null
}

fun shardInstance(): JavaPlugin {
    if (CrystalShard.plugin == null) {
        throw Exception("You need to use crystalshard(plugin)")
    }
    return CrystalShard.plugin!!
}


fun crystalshard(plugin: JavaPlugin) {
    CrystalShard.plugin = plugin
}