package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.moveEntity
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class MoveEntityTest : Test("MoveEntityTest") {
    override fun command() {
        test {
            val player = sender!!as Player

            ClientPacketFactory.moveEntity(
                mode = EntityMoveMode.POS_ROT,
                entity = player.getNearbyEntities(2.0, 2.0, 2.0).filter { it.type == EntityType.PIG }
                    .toMutableList()[0],
                xa = ((player.x * 4096) - (player.x * 4096)).toInt().toShort(),
                ya = ((player.y * 4096) - (player.y * 4096)).toInt().toShort(),
                za = ((player.z * 4096) - (player.z * 4096)).toInt().toShort(),
                yRot = (0 * 256.0 / 360.0).toInt().toByte(),
                xRot = (0 * 256.0 / 360.0).toInt().toByte(),
                onGround = true,
                hasRot = true,
                hasPos = true
            ) { it.send(mutableListOf(player)) }
            println("MoveEntity OK")
        }
    }
}