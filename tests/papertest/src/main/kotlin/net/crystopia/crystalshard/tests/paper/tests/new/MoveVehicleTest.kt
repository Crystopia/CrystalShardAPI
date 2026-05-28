package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3i
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveVehicle
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoveVehicleTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val loc = player.location
            ClientPacketFactory.moveVehicle(
                entity = player,
                position = Vec3(Vec3i(loc.x + 1, loc.y, loc.z), 0.0, 0.0, 0.0),
                yRot = loc.yaw,
                xRot = loc.pitch
            ) { it.send(mutableListOf(player)) }
            println("MoveVehicle OK")
        }
    }
}