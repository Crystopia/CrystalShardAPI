package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.particles.Particle

data class ClientboundLevelParticlesPacketData(
    var particle: Particle<*,*>,
)
