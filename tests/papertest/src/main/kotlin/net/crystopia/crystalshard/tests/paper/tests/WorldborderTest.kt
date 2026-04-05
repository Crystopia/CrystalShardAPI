package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.world.WorldBorder
import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class WorldborderTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val worldBorder = WorldBorder(
                world = Bukkit.getWorld("world")!!,
                size = 50.0,
                centerX = 0.0,
                centerZ = 0.0,
                absoluteMaxSize = null,
                safeZone = 0.0,
                warningBlocks = 0,
                warningTime = 0,
                oldLerpSize = 50.0,
                newLerpSize = 5.0,
                lerpTime = 10000,
                damagePerBlock = 1000.0,
                lerpTimeStart = null
            )

            sender.sendMessage(Component.text().text("<red>WorldBorder is a working feature... But the damage dealt to the player has some issues... as do the warnings and safety aspects...</red>"))
            ClientPacketFactory.initWorldBorder(
                worldBorder
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
            ClientPacketFactory.setWorldBorderLerpSize(
                worldBorder
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }

}