package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.particles.ParticleType
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.spawnParticle
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SpawnParticleTest(name: String, sender: CommandSender, args: CommandArguments) : Test("no_use") {
    override fun command() {
        test {
            val player = sender!!as Player
            val loc = player.location
            ClientPacketFactory.spawnParticle(
                particle = net.crystopia.crystalshard.paper.dhl.types.particles.Particle(
                    particle = ParticleType.FLAME,
                    options = null,
                    overrideLimiter = true,
                    alwaysShow = true,
                    x = loc.x,
                    y = loc.y + 1,
                    z = loc.z,
                    xOffSet = 0.5f,
                    yOffSet = 0.5f,
                    zOffSet = 0.5f,
                    maxSpeed = 0.1f,
                    count = 20
                )
            ) { it.send(mutableListOf(player)) }
            println("SpawnParticle OK")
        }
    }
}