package net.crystopia.crystalshard.paper.folia.extension

import io.papermc.paper.threadedregions.scheduler.EntityScheduler
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import net.crystopia.crystalshard.paper.core.shardInstance

fun EntityScheduler.run(retired: () -> Unit, task: ScheduledTask.() -> Unit): ScheduledTask? {
    return this.run(
        shardInstance(),
        task,
        retired
    )
}

fun EntityScheduler.run(task: ScheduledTask.() -> Unit): ScheduledTask? {
    return this.run(
        shardInstance(),
        task,
        null
    )
}

fun EntityScheduler.execute(delay: Long, task: () -> Unit) {
    execute(
        shardInstance(),
        task,
        null,
        delay,
    )
}

fun EntityScheduler.execute(delay: Long, retired: () -> Unit, task: () -> Unit) {
    execute(
        shardInstance(),
        task,
        retired,
        delay,
    )
}

fun EntityScheduler.runDelayed(
    initialDelay: Long,
    retired: () -> Unit,
    task: ScheduledTask.() -> Unit
): ScheduledTask? {
    return runDelayed(
        shardInstance(),
        task,
        retired,
        initialDelay,
    )
}

fun EntityScheduler.runDelayed(
    initialDelay: Long,
    task: ScheduledTask.() -> Unit
): ScheduledTask? {
    return runDelayed(
        shardInstance(),
        task,
        null,
        initialDelay,
    )
}

fun EntityScheduler.runAtFixedRate(
    initialDelay: Long,
    period: Long,
    retired: () -> Unit,
    task: ScheduledTask.() -> Unit
): ScheduledTask? {
    return runAtFixedRate(
        shardInstance(),
        task,
        retired,
        initialDelay,
        period
    )
}

fun EntityScheduler.runAtFixedRate(
    initialDelay: Long,
    period: Long,
    task: ScheduledTask.() -> Unit
): ScheduledTask? {
    return runAtFixedRate(
        shardInstance(),
        task,
        null,
        initialDelay,
        period
    )
}