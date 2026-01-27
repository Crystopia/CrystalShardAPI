package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.maps

import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapPatch
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

fun MapPatch.build(): MapItemSavedData.MapPatch {
    return MapItemSavedData.MapPatch(
        startX,
        startY,
        width,
        height,
        mapColors.toByteArray(),
    )
}