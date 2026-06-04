package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.playerInfoRemove
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayerInfoRemoveTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            ClientPacketFactory.playerInfoRemove(
                uuids = mutableListOf(player.uniqueId),
            ) { it.send(mutableListOf(player)) }
            println("PlayerInfoRemove OK → UUID: $player.uniqueId")
        }
    }
}