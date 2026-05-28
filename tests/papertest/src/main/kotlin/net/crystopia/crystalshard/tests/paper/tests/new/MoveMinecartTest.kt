package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.entities.MinecartStep
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3i
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveMinecart
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoveMinecartTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val loc = player.location
            ClientPacketFactory.moveMinecart(
                entityId = 9998,
                lerpSteps = mutableListOf(
                    MinecartStep(
                        position = Vec3(Vec3i(loc.x, loc.y, loc.z), 0.0, 0.0, 0.0),
                        movement = Vec3(Vec3i(0.1, 0.0, 0.0), 0.0, 0.0, 0.0),
                        yRot = 0f,
                        xRot = 0f,
                        weight = 1f
                    )
                )
            ) { it.send(mutableListOf(player)) }
            println("MoveMinecart OK")
        }
    }
}