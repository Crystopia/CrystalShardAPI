package net.crystopia.crystalshard.paper.core.scheduler

import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.concurrent.TimeUnit

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

// --- https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/scheduler/Scheduler.kt - MIT LICENSE ---

fun sync(runnable: ShardRunnable.() -> Unit): ShardRunnable {
    return ShardRunnable(runnable, false).apply { schedule() }
}

/**
 * Schedules an asynchronous task to be executed by the Bukkit scheduler.
 *
 * @param runnable The function representing the task to be executed.
 * @return The ShardRunnable representing the scheduled task.
 */
fun async(runnable: ShardRunnable.() -> Unit): ShardRunnable {
    return ShardRunnable(runnable, true).apply { schedule() }
}

/**
 * Schedules a delayed task to be executed by the Bukkit scheduler.
 *
 * @param value The duration value for the delay.
 * @param unit The TimeUnit representing the time unit of the delay (default: MILLISECONDS).
 * @param async Whether the task should be executed asynchronously (default: false).
 * @param runnable The function representing the task to be executed.
 * @return The ShardRunnable representing the scheduled task.
 */
fun delay(
    value: Long,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    async: Boolean = false,
    runnable: ShardRunnable.() -> Unit
): ShardRunnable {
    return ShardRunnable(runnable, async, unit.toMillis(value)).apply { schedule() }
}

/**
 * Schedules a repeating task to be executed by the Bukkit scheduler.
 *
 * @param delay The duration value for the initial delay.
 * @param period The duration value for the period between subsequent executions.
 * @param unit The TimeUnit representing the time unit of the delay and period (default: MILLISECONDS).
 * @param async Whether the task should be executed asynchronously (default: false).
 * @param runnable The function representing the task to be executed.
 * @return The BukkitTask representing the scheduled task.
 */
fun repeat(
    delay: Long,
    period: Long,
    unit: TimeUnit = TimeUnit.MILLISECONDS,
    async: Boolean = false,
    runnable: BukkitRunnable.() -> Unit
): ShardRunnable {

    return if (async) {
        ShardRunnable(runnable, true, 0).also {
            it.runTaskTimerAsynchronously(
                shardInstance(),
                unit.toMillis(delay),
                unit.toMillis(period)
            )
        }
    } else {
        ShardRunnable(runnable, false, 0).also {
            it.runTaskTimer(
                shardInstance(),
                unit.toMillis(delay),
                unit.toMillis(period)
            )
        }
    }
}