package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.gui.MenuType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.openScreen
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import net.kyori.adventure.text.Component
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class OpenScreenTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.openScreen(
                id = 1,
                title = Component.text("Test Inventory"),
                type = MenuType.GENERIC_9x3
            ) { it.send(mutableListOf(player)) }
            println("OpenScreen OK")
        }
    }
}