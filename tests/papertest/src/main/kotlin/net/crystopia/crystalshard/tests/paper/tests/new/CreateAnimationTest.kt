package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createAnimation
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CreateAnimationTest : Test("CreateAnimationTest") {
    override fun command() {
       test {
            val player = sender!!as Player
            // Animation ID 0 = Swing main arm
            ClientPacketFactory.createAnimation(
                entity = player,
                animationId = 0
            ) { it.send(mutableListOf(player)) }
            println("CreateAnimation OK")
        }
    }
}