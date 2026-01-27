package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.maps

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.maps.MapDecorationType
import java.util.Optional

fun MapDecoration.build(): net.minecraft.world.level.saveddata.maps.MapDecoration {
    return net.minecraft.world.level.saveddata.maps.MapDecoration(
        MapDecorationType.convert(type).id,
        x,
        y,
        rot,
        Optional.ofNullable(PaperAdventure.asVanilla(name)),
    )
}