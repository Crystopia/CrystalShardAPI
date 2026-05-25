package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.maps

import net.crystopia.crystalshard.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.maps.MapDecorationType
import java.util.*

fun MapDecoration.build(): net.minecraft.world.level.saveddata.maps.MapDecoration {
    return net.minecraft.world.level.saveddata.maps.MapDecoration(
        MapDecorationType.convert(type).id,
        x,
        y,
        rot,
        Optional.ofNullable(name),
    )
}