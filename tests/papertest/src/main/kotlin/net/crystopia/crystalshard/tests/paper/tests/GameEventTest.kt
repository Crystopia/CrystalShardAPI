package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.enums.game.GameEventType
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameEventTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.runGameEvent(
                type = GameEventType.CHANGE_GAME_MODE,
                action = 2F
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}