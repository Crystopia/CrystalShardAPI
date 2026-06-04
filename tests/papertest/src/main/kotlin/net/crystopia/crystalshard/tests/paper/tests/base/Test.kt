package net.crystopia.crystalshard.tests.paper.tests.base

import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import net.crystopia.crystalshard.common.log.Log
import net.crystopia.crystalshard.common.log.green
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender

abstract class Test(val name: String) {

    var sender: CommandSender? = null
        private set
    var args: CommandArguments? = null
        private set
    private var msStart: Long? = null
    private var test: () -> Unit = {}
    private var instance: Test

    init {
        println("Test has been created with name $name".green())
        register()
        instance = this
    }

    abstract fun command()

    fun test(test: () -> Unit): Test {
        this.test = test
        run()
        return this
    }

    private fun run() {
        this.msStart = System.nanoTime()
        try {
            this.sender!!.sendMessage("Running test...")
            this.test.invoke()
            this.sender!!.sendMessage("Send executed...")
            val durationNano: Long = System.nanoTime() - msStart!!
            success(durationNano)
        } catch (e: Exception) {
            error(e)
        }
    }

    private fun error(e: Exception) {
        Log.error(e.toString())
        this.sender!!.sendMessage(
            MiniMessage.miniMessage()
                .deserialize("\n<color:#ff0015>Your test did not run successfully!</color>\n\n<b><i>>></i></b> <b>Details</b>:\n   <gray><u>Name</u>:</gray> $name\n   <gray><u>Message</u>:</gray> ${e.message}\n\n<b><i>>></i></b> <b>Exception</b>:\n<dark_gray>[</dark_gray>\n<color:#b3b3b3> $e</color>\n<dark_gray>]</dark_gray>\n")
        )
    }

    private fun success(ms: Long) {
        this.sender!!.sendMessage(
            MiniMessage.miniMessage()
                .deserialize("\n<color:#00ff26>Your test was successfully completed in <gray>$ms ns</gray>.</color>\n<u><gray>Name</gray></u>: $name\n")
        )
    }

    private fun register() {
        commandTree(name.lowercase()) {
            anyExecutor { sender, arguments ->
                instance.sender = sender
                instance.args = arguments
                instance.command()
            }
        }
    }
}