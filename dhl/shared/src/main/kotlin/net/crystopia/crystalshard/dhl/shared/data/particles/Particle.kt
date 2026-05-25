package net.crystopia.crystalshard.dhl.shared.data.particles

import net.crystopia.crystalshard.dhl.shared.enums.particles.ParticleType

data class Particle<T : ParticleType, O : ParticleOptions<*, *>>(
    var particle: T,
    var options: O?,
    var overrideLimiter: Boolean,
    var alwaysShow: Boolean,
    var x: Double,
    var y: Double,
    var z: Double,
    var xOffSet: Float,
    var yOffSet: Float,
    var zOffSet: Float,
    var maxSpeed: Float,
    var count: Int,
)
