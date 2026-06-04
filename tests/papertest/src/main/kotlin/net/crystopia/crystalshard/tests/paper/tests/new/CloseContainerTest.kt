package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.closeContainer
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.entity.Player

class CloseContainerTest : Test("CloseContainerTest") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.closeContainer(
                id = 0
            ) { it.send(mutableListOf(player)) }
            println("CloseContainer OK")
        }
    }
}