package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CooldownTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.applyCooldown(
                Material.PAPER, 1000
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}