package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createBlockDestroyStage
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.entity.Player

class CreateBlockDestroyStageTest : Test("CreateBlockDestroyStageTest") {
    override fun command() {
       test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.createBlockDestroyStage(
                entityId = player.entityId,
                pos = BlockPos(loc.blockX, loc.blockY - 1, loc.blockZ),
                progress = 5
            ) { it.send(mutableListOf(player)) }
            println("CreateBlockDestroyStage OK")
        }
    }
}