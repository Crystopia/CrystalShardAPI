package net.crystopia.crystalshard.paper.core.extension

import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.event.Listener

fun Listener.register() {
    shardInstance().server.pluginManager.registerEvents(this, shardInstance())
}