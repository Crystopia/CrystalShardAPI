package net.crystopia.crystalshard.tests.paper

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import net.crystopia.crystalshard.paper.pack.toasts.Toast
import net.crystopia.crystalshard.paper.pack.toasts.toast

object TestyCommand {

    val command = commandTree("crystal", "crystalshard") {
        literalArgument("toast") {
            playerExecutor { player, arguments ->
                player.toast("Joining minigame...", Toast.ToastTypes.ADVANCEMENT)
            }
        }
    }

}