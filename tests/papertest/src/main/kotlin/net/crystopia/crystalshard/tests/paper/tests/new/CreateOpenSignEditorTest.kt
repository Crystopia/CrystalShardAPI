package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.enums.particles.ParticleType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.createOpenSignEditor
import net.crystopia.crystalshard.paper.dhl.packets.client.spawnParticle
import net.crystopia.crystalshard.paper.dhl.types.particles.Particle
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CreateOpenSignEditorTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player
            val loc = player.location
            ClientPacketFactory.createOpenSignEditor(
                blockPos = BlockPos(loc.blockX, loc.blockY, loc.blockZ),
                isFrontText = true
            ) { it.send(mutableListOf(player)) }
            println("CreateOpenSignEditor OK")
        }
    }
}