package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.pack.font.TextHeads
import net.crystopia.crystalshard.paper.pack.toasts.Toast
import net.crystopia.crystalshard.paper.pack.toasts.toast
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.UUID

class PlayerHeadTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val head = TextHeads.generateHead(
                UUID.fromString("f6f3a530-6c39-4098-96a0-6bdf4f3afc70"), true
            )
            sender!!.sendMessage(head)

            (sender!!as Player).toast("Cool", Toast.ToastTypes.ADVANCEMENT)

            /*
            (sender!!as Player).toast("Cool", Toast.ToastTypes.TUTORIAL)
            (sender!!as Player).toast("Cool", Toast.ToastTypes.DEFAULT)
            (sender!!as Player).toast("Cool", Toast.ToastTypes.RECIPE)
            (sender!!as Player).toast("Cool", Toast.ToastTypes.SYSTEM)
             */
        }
    }
}