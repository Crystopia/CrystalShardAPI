package net.crystopia.crystalshard.tests.paper.tests

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.Particle
import net.crystopia.crystalshard.paper.dhl.shared.enums.particles.ParticleType
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ParticleTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            ClientPacketFactory.spawnParticle(
                particle = Particle(
                    particle = ParticleType.NOTE,
                    options = null,
                    overrideLimiter = true,
                    alwaysShow = false,
                    x = 1.0,
                    y = 1.0,
                    z = 1.0,
                    xOffSet = 2.0F,
                    yOffSet = 2.0F,
                    zOffSet = 2.0F,
                    maxSpeed = 5F,
                    count = 500
                )
            ) { packet ->
                packet.send(mutableListOf(sender as Player))
            }
        }
    }
}