package net.crystopia.crystalshard.paper.folia.extension

import io.papermc.paper.threadedregions.scheduler.AsyncScheduler
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import net.crystopia.crystalshard.paper.core.shardInstance
import java.util.concurrent.TimeUnit

fun AsyncScheduler.run(task: ScheduledTask.() -> Unit) {
    runNow(
        shardInstance(),
        task
    )
}

fun AsyncScheduler.runDelayed(time: TimeUnit, delay: Long, task: ScheduledTask.() -> Unit) {
    runDelayed(
        shardInstance(),
        task,
        delay,
        time
    )
}

fun AsyncScheduler.runAtFixedRate(
    time: TimeUnit,
    initialDelay: Long,
    period: Long,
    task: ScheduledTask.() -> Unit
) {
    runAtFixedRate(
        shardInstance(),
        task,
        initialDelay,
        period,
        time
    )
}