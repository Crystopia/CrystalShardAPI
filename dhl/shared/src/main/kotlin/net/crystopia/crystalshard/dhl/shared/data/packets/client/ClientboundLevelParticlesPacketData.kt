package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.particles.Particle

data class ClientboundLevelParticlesPacketData(
    var particle: Particle<*,*>,
)
