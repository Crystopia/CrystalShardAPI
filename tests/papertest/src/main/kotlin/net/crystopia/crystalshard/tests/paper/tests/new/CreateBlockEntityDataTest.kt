package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createBlockEntityData
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CreateBlockEntityDataTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val loc = player.location
            ClientPacketFactory.createBlockEntityData(
                blockPos = BlockPos(loc.blockX, loc.blockY, loc.blockZ),
                type = net.crystopia.crystalshard.dhl.shared.enums.entities.BlockEntityType.SIGN,
                nbt = "{}"
            ) { it.send(mutableListOf(player)) }
            println("CreateBlockEntityData OK")
        }
    }
}