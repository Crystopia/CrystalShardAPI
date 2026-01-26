package net.crystopia.crystalshard.paper.core

import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.plugin.java.JavaPlugin

var plugin: JavaPlugin? = null

fun crystalshard(plugin: JavaPlugin) {
    net.crystopia.crystalshard.paper.core.plugin = plugin
}