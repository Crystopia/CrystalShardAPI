package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.world

import net.minecraft.world.level.border.WorldBorder
import org.bukkit.craftbukkit.CraftWorld

fun net.crystopia.crystalshard.paper.dhl.shared.data.world.WorldBorder.build(): WorldBorder {
    val worldBorder = WorldBorder()
    worldBorder.setCenter(centerX, centerZ)
    worldBorder.size = size
    if (absoluteMaxSize != null) worldBorder.absoluteMaxSize = absoluteMaxSize!!
    worldBorder.world = (world as CraftWorld).handle
    if (damagePerBlock != null) worldBorder.damagePerBlock = damagePerBlock!!
    if (safeZone != null) worldBorder.damageSafeZone = safeZone!!
    if (warningBlocks != null) worldBorder.warningBlocks = warningBlocks!!
    if (warningTime != null) worldBorder.warningTime = warningTime!!
    if (oldLerpSize != null && newLerpSize != null && lerpTime != null) worldBorder.lerpSizeBetween(
        oldLerpSize!!,
        newLerpSize!!,
        lerpTime!!
    )

    return worldBorder
}