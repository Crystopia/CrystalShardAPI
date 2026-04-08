package net.crystopia.crystalshard.paper.core.extension

import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

fun taskLater(delay: Long, task: () -> Unit): BukkitTask {
    return Bukkit.getServer().scheduler.runTaskLater(shardInstance(), task, delay)
}

fun taskLaterAsync(delay: Long, task: () -> Unit): BukkitTask {
    return Bukkit.getServer().scheduler.runTaskLaterAsynchronously(shardInstance(), task, delay)
}

fun taskTimer(delay: Long, period: Long, task: () -> Unit): BukkitTask {
    return Bukkit.getServer().scheduler.runTaskTimer(shardInstance(), task, delay, period)
}

fun taskTimerAsync(delay: Long, period: Long, task: () -> Unit): BukkitTask {
    return Bukkit.getServer().scheduler.runTaskTimerAsynchronously(shardInstance(), task, delay, period)
}