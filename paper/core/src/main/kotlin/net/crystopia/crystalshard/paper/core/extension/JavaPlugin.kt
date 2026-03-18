package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.addEvent(listener: Listener) {
    server.pluginManager.registerEvents(listener, this)
}

fun JavaPlugin.requirePlugin(plugin: String) {
    if (!server.pluginManager.isPluginEnabled(plugin)) {
        throw Exception("Plugin can't start without $plugin!")
    } else server.logger.info("[Server] ${this.name} is hooking in Plugin $plugin...")
}

fun JavaPlugin.task(task: () -> Unit) {
    server.scheduler.runTask(this, task)
}

fun JavaPlugin.async(task: () -> Unit) {
    server.scheduler.runTaskAsynchronously(this, task)
}

fun JavaPlugin.timerAsync(
    delay: Long, period: Long, task: () -> Unit
) {
    server.scheduler.runTaskTimerAsynchronously(this, task, delay, period)
}

fun JavaPlugin.timer(
    delay: Long, period: Long, task: () -> Unit
) {
    server.scheduler.runTaskTimer(this, task, delay, period)
}

fun JavaPlugin.taskLater(
    delay: Long, task: () -> Unit
) {
    server.scheduler.runTaskLater(this, task, delay)
}

fun JavaPlugin.taskLaterAsync(
    delay: Long, task: () -> Unit
) {
    server.scheduler.runTaskLaterAsynchronously(this, task, delay)
}