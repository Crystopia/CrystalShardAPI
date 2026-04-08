package net.crystopia.crystalshard.paper.core.scheduler

import net.crystopia.crystalshard.paper.core.shardInstance
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.util.concurrent.TimeUnit

// Credits and Inspiration from https://github.com/flytegg/twilight/blob/master/src/main/kotlin/gg/flyte/twilight/scheduler/ShardRunnable.kt - MIT LICENSE

class ShardRunnable(
    val task: ShardRunnable.() -> Unit,
    val async: Boolean = false,
    val delay: Long = 0,
) : BukkitRunnable() {

    private var nextRunnable: ShardRunnable? = null

    override fun run() {
        try {
            task()
        } finally {

        }
    }

    /**
     * Define a runnable to executed after the completion of this runnable,
     * This runnable will run the same async state of the previous runnable
     * @param delay delay before this task should be executed
     * @param unit The unit of time the delay is in
     * @param action The task to be executed
     * @return The new ShardRunnable
     */
    fun onComplete(
        delay: Long = 0,
        unit: TimeUnit = TimeUnit.MILLISECONDS,
        action: ShardRunnable.() -> Unit
    ): ShardRunnable {
        return chainRunnable(action, async, unit.toMillis(delay))
    }

    /**
     * Define a runnable to executed synchronously after the completion of this runnable
     * @param delay delay before this task should be executed
     * @param unit The unit of time the delay is in
     * @param action The task to be executed
     * @return The new ShardRunnable
     */
    fun onCompleteSync(
        delay: Long = 0,
        unit: TimeUnit = TimeUnit.MILLISECONDS,
        action: ShardRunnable.() -> Unit
    ): ShardRunnable {
        return chainRunnable(action, false, unit.toMillis(delay))
    }

    /**
     * Define a runnable to executed asynchronously after the completion of this runnable
     * @param delay delay before this task should be executed
     * @param unit The unit of time the delay is in
     * @param action The task to be executed
     * @return The new ShardRunnable
     */
    fun onCompleteAsync(
        delay: Long = 0,
        unit: TimeUnit = TimeUnit.MILLISECONDS,
        action: ShardRunnable.() -> Unit
    ): ShardRunnable {
        return chainRunnable(action, true, unit.toMillis(delay))
    }

    /**
     * Chains a new task to be executed after the current one completes
     * @param action The task to be executed
     * @param async Whether the new task is async
     * @param delay The delay before the new task should execute in ticks
     * @return The new ShardRunnable
     */
    private fun chainRunnable(action: ShardRunnable.() -> Unit, async: Boolean, delay: Long = 0): ShardRunnable {
        val newRunnable = ShardRunnable(action, async, delay)
        if (nextRunnable == null) {
            nextRunnable = newRunnable
        } else {
            var last = nextRunnable
            while (last?.nextRunnable != null) {
                last = last.nextRunnable
            }
            last?.nextRunnable = newRunnable
        }
        return newRunnable
    }

    fun schedule(delay: Long = 0): BukkitTask {
        val validatedAdditionalDelay = delay
        return if (async) {
            if (validatedAdditionalDelay > 0) this.runTaskLaterAsynchronously(shardInstance(), validatedAdditionalDelay)
            else this.runTaskAsynchronously(shardInstance())
        } else {
            if (validatedAdditionalDelay > 0) this.runTaskLater(shardInstance(), validatedAdditionalDelay)
            else this.runTask(shardInstance())
        }
    }

}