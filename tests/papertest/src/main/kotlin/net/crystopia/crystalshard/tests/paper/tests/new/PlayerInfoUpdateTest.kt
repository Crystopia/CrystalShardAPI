package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.playerInfoUpdate
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayerInfoUpdateTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.playerInfoUpdate(
                serverPlayer = player,
                actions = mutableListOf(
                    InfoUpdateAction.UPDATE_DISPLAY_NAME,
                    InfoUpdateAction.UPDATE_LISTED
                )
            ) { it.send(mutableListOf(player)) }
            println("PlayerInfoUpdate OK")
        }
    }
}