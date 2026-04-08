package net.crystopia.crystalshard.paper.folia

import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.Bukkit

fun threadedRunTask(run: ScheduledTask.() -> Unit) {
    Bukkit.getGlobalRegionScheduler().run(
        shardInstance(),
        run
    )
}

fun threadedDelayedTask(delay: Long, run: ScheduledTask.() -> Unit): ScheduledTask {
    return Bukkit.getGlobalRegionScheduler().runDelayed(
        shardInstance(),
        run,
        delay
    )
}

fun threadedRepeatedTask(initialDelayTicks: Long, periodTicks: Long, run: ScheduledTask.() -> Unit): ScheduledTask {
    return Bukkit.getGlobalRegionScheduler().runAtFixedRate(
        shardInstance(),
        run,
        initialDelayTicks,
        periodTicks
    )
}

fun threadedTask(run: () -> Unit) {
    Bukkit.getGlobalRegionScheduler().execute(
        shardInstance(), run
    )
}