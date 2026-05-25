package net.crystopia.crystalshard.paper.dhl.types.world

import org.bukkit.World

data class WorldBorder(
    var world: World,
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
    var lerpTimeStart: Long?
)