package net.crystopia.crystalshard.paper.core.extension

import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit

fun taskLater(delay: Long, task: () -> Unit) {
    Bukkit.getScheduler().runTaskLater(shardInstance(), task, delay)
}

fun taskLaterAsync(delay: Long, task: () -> Unit) {
    Bukkit.getScheduler().runTaskLaterAsynchronously(shardInstance(), task, delay)
}

fun taskTimer(delay: Long, period: Long, task: () -> Unit) {
    Bukkit.getScheduler().runTaskTimer(shardInstance(), task, delay, period)
}

fun taskTimerAsync(delay: Long, period: Long, task: () -> Unit) {
    Bukkit.getScheduler().runTaskTimerAsynchronously(shardInstance(), task, delay, period)

}