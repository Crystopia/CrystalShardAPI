package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.PacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.updateAdvancements
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class UpdateAdvancements(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            PacketFactory.client.updateAdvancements(
                reset = true,
                added = mutableListOf(),
                removed = mutableSetOf(),
                progress = mutableMapOf(),
                showAdvancements = true
            ) {
                it.send(mutableListOf(sender as Player))
            }
        }
    }
}