package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.addEntity
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*

class AddEntityTest : Test("AddEntity") {

    override fun command() {
        test {
            val player = sender!!as Player
            val players = mutableListOf(player)

            val spawnLoc = player.location.clone().add(
                player.location.direction.multiply(3)
            )

            ClientPacketFactory.addEntity(
                entityId = 9999,
                entityUUID = UUID.randomUUID(),
                location = spawnLoc,
                entityType = EntityType.ZOMBIE,
                data = 0,
                yHeadRot = player.location.yaw.toDouble()
            ) {
                it.send(players)
            }

            println("AddEntity OK → ${spawnLoc.x}, ${spawnLoc.y}, ${spawnLoc.z}")
        }
    }
}