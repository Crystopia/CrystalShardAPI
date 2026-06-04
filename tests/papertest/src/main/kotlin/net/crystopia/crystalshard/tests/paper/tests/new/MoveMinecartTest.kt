package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.entities.MinecartStep
import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveMinecart
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class MoveMinecartTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val entity = player.getNearbyEntities(2.0, 2.0, 2.0).filter { it.type == EntityType.MINECART }
                .toMutableList()[0]

            ClientPacketFactory.moveMinecart(
                entity = entity,
                lerpSteps = mutableListOf(
                    MinecartStep(
                        position = Vec3(1.0, 0.0, 0.0),
                        movement = Vec3(0.0, 0.0, 0.0),
                        yRot = 0f,
                        xRot = 0f,
                        weight = 0f
                    ),
                    MinecartStep(
                        position = Vec3(0.0, 0.0, 0.0),
                        movement = Vec3(0.0, 0.0, 0.0),
                        yRot = 0f,
                        xRot = 0f,
                        weight = 0f
                    )
                )
            ) { it.send(mutableListOf(player)) }
            println("MoveMinecart OK")
        }
    }
}