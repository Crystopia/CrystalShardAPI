package net.crystopia.crystalshardtest

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import net.crystopia.crystalshard.extras.toasts.Toast
import net.crystopia.crystalshard.extras.toasts.toast

object TestyCommand {

    val command = commandTree("crystal", "crystalshard") {
        literalArgument("toast") {
            playerExecutor { player, arguments ->
                player.toast("Joining minigame...", Toast.ToastTypes.TUTORIAL)
            }
        }
    }

}