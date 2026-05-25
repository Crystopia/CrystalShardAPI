package net.crystopia.crystalshard.dhl.shared.data.custom

import net.minecraft.world.level.Level

data class Location(
    val world: Level,
    val x: Double, var y: Double, var z: Double,
    var yaw: Float, var pitch: Float
)
