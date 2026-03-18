package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.core.extension.kill
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RespawnTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.playRespawnPacket(
                world = Bukkit.getWorld("world_the_end")!!,
                deathLocation = Location(Bukkit.getWorld("world")!!, 0.0, 0.0, 0.0),
                gameMode = GameMode.CREATIVE,
                isDebug = false,
                isFlat = false,
                portalCooldown = 100000,
                datakept = 0x01,
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }

          (sender as Player).kill()
        }
    }

}