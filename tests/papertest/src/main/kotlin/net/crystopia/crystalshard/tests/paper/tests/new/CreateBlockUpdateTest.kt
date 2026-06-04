package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createBlockUpdate
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.Material
import org.bukkit.entity.Player

class CreateBlockUpdateTest : Test("CreateBlockUpdateTest") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.createBlockUpdate(
                pos = BlockPos(loc.blockX, loc.blockY - 1, loc.blockZ),
                state = Material.DIAMOND_BLOCK
            ) { it.send(mutableListOf(player)) }
            println("CreateBlockUpdate OK → Siehst du einen Diamantblock unter dir?")
        }
    }
}