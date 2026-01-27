package net.crystopia.crystalshard.paper.dhl.shared.data.world

import net.minecraft.world.level.border.WorldBorder
import org.bukkit.World
import org.bukkit.craftbukkit.CraftWorld

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
)