package net.crystopia.crystalshard.dhl.shared.data.custom

data class Location(
    /**
     * ServerLevel
     */
    val world: Any,
    val x: Double, var y: Double, var z: Double,
    var yaw: Float, var pitch: Float
)
