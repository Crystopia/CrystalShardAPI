package net.crystopia.crystalshard.paper.folia.extension

import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import net.crystopia.crystalshard.paper.core.shardInstance

fun GlobalRegionScheduler.execute(task: () -> Unit) {
    this.execute(
        shardInstance(),
        task,
    )
}

fun GlobalRegionScheduler.run(task: ScheduledTask.() -> Unit): ScheduledTask {
    return this.run(
        shardInstance(),
        task
    )
}


fun GlobalRegionScheduler.runDelayed(
    initialDelay: Long,
    task: ScheduledTask.() -> Unit
): ScheduledTask {
    return runDelayed(
        shardInstance(),
        task,
        initialDelay,
    )
}


fun GlobalRegionScheduler.runAtFixedRate(
    initialDelay: Long,
    period: Long,
    task: ScheduledTask.() -> Unit
): ScheduledTask {
    return runAtFixedRate(
        shardInstance(),
        task,
        initialDelay,
        period
    )
}