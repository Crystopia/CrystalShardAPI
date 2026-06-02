package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.world

import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.border.WorldBorder

fun net.crystopia.crystalshard.dhl.shared.data.world.WorldBorder.build(): WorldBorder {
    val worldBorder = WorldBorder()
    worldBorder.setCenter(centerX, centerZ)
    worldBorder.size = size
    if (absoluteMaxSize != null) worldBorder.absoluteMaxSize = absoluteMaxSize!!
    worldBorder.world = (world as ServerLevel)
    if (damagePerBlock != null) worldBorder.damagePerBlock = damagePerBlock!!
    if (safeZone != null) worldBorder.safeZone = safeZone!!
    if (warningBlocks != null) worldBorder.warningBlocks = warningBlocks!!
    if (warningTime != null) worldBorder.warningTime = warningTime!!
    if (oldLerpSize != null && newLerpSize != null && lerpTime != null && lerpTimeStart != null) worldBorder.lerpSizeBetween(
        oldLerpSize!!,
        newLerpSize!!,
        lerpTime!!,
        lerpTimeStart!!
    )

    return worldBorder
}