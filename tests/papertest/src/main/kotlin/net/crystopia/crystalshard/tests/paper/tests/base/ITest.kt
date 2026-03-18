package net.crystopia.crystalshard.tests.paper.tests.base

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.core.utils.Log
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender

abstract class ITest(var name: String, var sender: CommandSender, var args: CommandArguments) {

    private var msStart: Long? = null
    private var test: () -> Unit = {}

    abstract fun command()
    fun test(test: () -> Unit) {
        this.test = test
        run()
    }

    private fun run() {
        this.msStart = System.nanoTime()
        try {
            this.sender.sendMessage("Running test...")
            this.test.invoke()
            this.sender.sendMessage("Send executed...")
            val durationNano: Long = System.nanoTime() - msStart!!
            success(durationNano)
        } catch (e: Exception) {
            error(e)
        }
    }

    private fun error(e: Exception) {
        Log.error(e.toString())
        this.sender.sendMessage(
            MiniMessage.miniMessage()
                .deserialize("\n<color:#ff0015>Your test did not run successfully!</color>\n\n<b><i>>></i></b> <b>Details</b>:\n   <gray><u>Name</u>:</gray> $name\n   <gray><u>Message</u>:</gray> ${e.message}\n\n<b><i>>></i></b> <b>Exception</b>:\n<dark_gray>[</dark_gray>\n<color:#b3b3b3> $e</color>\n<dark_gray>]</dark_gray>\n")
        )
    }

    private fun success(ms: Long) {
        this.sender.sendMessage(
            MiniMessage.miniMessage()
                .deserialize("\n<color:#00ff26>Your test was successfully completed in <gray>$ms ns</gray>.</color>\n<u><gray>Name</gray></u>: $name\n")
        )
    }

}