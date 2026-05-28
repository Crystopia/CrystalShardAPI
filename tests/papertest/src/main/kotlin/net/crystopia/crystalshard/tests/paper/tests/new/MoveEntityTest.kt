package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveEntity
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoveEntityTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            ClientPacketFactory.moveEntity(
                mode = EntityMoveMode.POS_ROT,
                entity = player,
                xa = 100,
                ya = 0,
                za = 0,
                yRot = 0,
                xRot = 0,
                onGround = true,
                hasRot = true,
                hasPos = true
            ) { it.send(mutableListOf(player)) }
            println("MoveEntity OK")
        }
    }
}