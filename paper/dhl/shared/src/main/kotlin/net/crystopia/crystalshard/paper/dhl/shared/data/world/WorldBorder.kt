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
) {
    fun build(): WorldBorder {
        val worldBorder = WorldBorder()
        worldBorder.setCenter(centerX, centerZ)
        worldBorder.size = size
        if (absoluteMaxSize != null) worldBorder.absoluteMaxSize = absoluteMaxSize!!
        worldBorder.world = (world as CraftWorld).handle
        if (damagePerBlock != null) worldBorder.damagePerBlock = damagePerBlock!!
        if (safeZone != null) worldBorder.safeZone = safeZone!!
        if (warningBlocks != null) worldBorder.warningBlocks = warningBlocks!!
        if (warningTime != null) worldBorder.warningTime = warningTime!!
        if (oldLerpSize != null && newLerpSize != null && lerpTime != null) worldBorder.lerpSizeBetween(
            oldLerpSize!!,
            newLerpSize!!,
            lerpTime!!
        )

        return worldBorder
    }
}