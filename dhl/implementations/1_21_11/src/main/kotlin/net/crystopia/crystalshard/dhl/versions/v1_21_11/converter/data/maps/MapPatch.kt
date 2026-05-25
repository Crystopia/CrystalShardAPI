package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.maps

import net.crystopia.crystalshard.dhl.shared.data.maps.MapPatch
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