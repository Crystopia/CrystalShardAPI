package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.applyCooldown
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ApplyCooldownTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.applyCooldown(
                item = Material.ENDER_PEARL,
                duration = 200
            ) { it.send(mutableListOf(player)) }
            println("ApplyCooldown OK")
        }
    }
}