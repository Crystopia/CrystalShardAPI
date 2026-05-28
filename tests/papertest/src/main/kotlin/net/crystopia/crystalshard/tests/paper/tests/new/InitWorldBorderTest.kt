package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.initWorldBorder
import net.crystopia.crystalshard.paper.dhl.types.world.WorldBorder
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InitWorldBorderTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.initWorldBorder(
                border = WorldBorder(
                    world = player.world,
                    size = 1000.0,
                    centerX = 0.0,
                    centerZ = 0.0,
                    absoluteMaxSize = 29999984,
                    damagePerBlock = 0.2,
                    safeZone = 5.0,
                    warningBlocks = 5,
                    warningTime = 15,
                    oldLerpSize = 1000.0,
                    newLerpSize = 900.0,
                    lerpTime = 200L,
                    lerpTimeStart = System.currentTimeMillis(),
                )
            ) { it.send(mutableListOf(player)) }
            println("InitWorldBorder OK")
        }
    }
}