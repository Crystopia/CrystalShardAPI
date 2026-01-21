package net.crystopia.crystalshard.paper.dhl.shared.data.particles

data class Particle<T : ParticleOptions>(
    var particle: T,
    var overrideLimiter: Boolean,
    var alwaysShow: Boolean,
    var x: Double,
    var y: Double,
    var z: Double,
    var xDist: Double,
    var yDist: Float,
    var zDist: Float,
    var maxSpeed: Float,
    var count: Int
)
