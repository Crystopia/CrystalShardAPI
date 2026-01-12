package net.crystopia.crystalshard.paper.core

import org.bukkit.plugin.java.JavaPlugin

var plugin: JavaPlugin
    get() {

        require(plugin != null) {
            "Please init CrystalShard first. Use crystalshard()"
        }

        return plugin
    }
    internal set(value) {
        plugin = value
    }

fun crystalshard(plugin: JavaPlugin) {
    net.crystopia.crystalshard.paper.core.plugin = plugin
}