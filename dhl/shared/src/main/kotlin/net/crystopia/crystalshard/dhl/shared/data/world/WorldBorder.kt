package net.crystopia.crystalshard.dhl.shared.data.world

import net.minecraft.world.level.Level

data class WorldBorder(
    var world: Level,
    var size: Double,
    var centerX: Double,
    var centerZ: Double,
    var absoluteMaxSize: Int?,
    var damagePerBlock: Double?,
    var safeZone: Double?,
    var warningBlocks: Int?,
    var warningTime: Int?,
    var oldLerpSize: Double?,
    var newLerpSize: Double?,
    var lerpTime: Long?,
    var lerpTimeStart : Long?
)