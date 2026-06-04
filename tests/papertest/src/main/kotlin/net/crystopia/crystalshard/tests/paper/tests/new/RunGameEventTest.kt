package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.game.GameEventType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.runGameEvent
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RunGameEventTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            // GameEventType.START_RAINING = startet Regen clientseitig
            ClientPacketFactory.runGameEvent(
                type = GameEventType.START_RAINING,
                action = 0f
            ) { it.send(mutableListOf(player)) }
            println("RunGameEvent OK → Client-seitiger Regen gestartet")
        }
    }
}