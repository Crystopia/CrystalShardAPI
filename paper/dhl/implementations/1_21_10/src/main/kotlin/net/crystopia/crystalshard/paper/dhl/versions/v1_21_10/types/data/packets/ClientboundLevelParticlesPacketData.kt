package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.particles.Particle

data class ClientboundLevelParticlesPacketData(
    var particle: Particle<*,*>,
)
