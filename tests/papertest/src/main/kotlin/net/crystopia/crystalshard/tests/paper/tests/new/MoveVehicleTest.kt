package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveVehicle
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class MoveVehicleTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.moveVehicle(
                entity = player.getNearbyEntities(2.0, 2.0, 2.0).filter { it.type == EntityType.BOAT }
                    .toMutableList()[0],
                position = Vec3(0.0, 0.0, 0.0),
                yRot = 1F,
                xRot = 1F
            ) { it.send(mutableListOf(player)) }

            println("MoveVehicle OK")
        }
    }
}