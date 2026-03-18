package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeleportTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.teleportEntityPacket(
                entityId = (sender as Player).entityId,
                location = Location(Bukkit.getWorld("world_the_end")!!, 0.0, 0.0, 0.0),
                onGround = false
            ) { packet ->
                packet.send(mutableListOf((sender as Player)))
            }
        }
    }

}