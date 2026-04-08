package net.crystopia.crystalshard.paper.folia

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.cancelThreadedTasks() {
    Bukkit.getGlobalRegionScheduler().cancelTasks(this)
}