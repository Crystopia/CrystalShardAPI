package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.core.extension.spawnEntity
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.sendEntityEvent
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Chicken
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class PacketTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {

    override fun command() {
        test {

            ClientPacketFactory.sendEntityEvent(
                sender as Player,
                35.toByte(),
                Bukkit.getWorld("world")!!
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }


        }
    }

}