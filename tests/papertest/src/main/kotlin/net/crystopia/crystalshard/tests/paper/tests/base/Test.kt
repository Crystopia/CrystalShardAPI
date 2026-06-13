package net.crystopia.crystalshard.tests.paper.tests.base

import dev.jorel.commandapi.executors.CommandArguments
import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
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
        println("Test has been created with name $name")
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
            this.sender!!.sendMessage("Running test...")
            this.test.invoke()
            this.sender!!.sendMessage("Send executed...")
            val durationNano: Long = System.nanoTime() - msStart!!
            success(durationNano)
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