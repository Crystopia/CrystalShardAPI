package net.crystopia.crystalshard.paper.core

import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.plugin.java.JavaPlugin

private var plugin: JavaPlugin? = null

fun shardInstance(): JavaPlugin {
    if (plugin == null){
        throw Exception("You need to use crystalshard(plugin)")
    }
    return plugin!!
}


fun crystalshard(plugin: JavaPlugin) {
    net.crystopia.crystalshard.paper.core.plugin = plugin
}