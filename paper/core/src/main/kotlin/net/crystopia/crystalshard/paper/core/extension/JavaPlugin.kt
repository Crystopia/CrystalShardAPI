package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

fun JavaPlugin.onEvent(listener: Listener) {
    server.pluginManager.registerEvents(listener, this)
}

fun JavaPlugin.requirePlugin(plugin: String) {
    if (!server.pluginManager.isPluginEnabled(plugin)) {
        throw Exception("Plugin can't start without $plugin!")
    } else server.logger.info("[Server] ${this.name} is hooking in Plugin $plugin...")
}

fun JavaPlugin.task(task: () -> Unit): BukkitTask {
    return server.scheduler.runTask(this, task)
}

fun JavaPlugin.async(task: () -> Unit): BukkitTask {
    return server.scheduler.runTaskAsynchronously(this, task)
}

fun JavaPlugin.timerAsync(
    delay: Long, period: Long, task: () -> Unit
): BukkitTask {
    return server.scheduler.runTaskTimerAsynchronously(this, task, delay, period)
}

fun JavaPlugin.timer(
    delay: Long, period: Long, task: () -> Unit
): BukkitTask {
    return server.scheduler.runTaskTimer(this, task, delay, period)
}

fun JavaPlugin.taskLater(
    delay: Long, task: () -> Unit
): BukkitTask {
    return server.scheduler.runTaskLater(this, task, delay)
}

fun JavaPlugin.taskLaterAsync(
    delay: Long, task: () -> Unit
): BukkitTask {
    return server.scheduler.runTaskLaterAsynchronously(this, task, delay)
}