package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.maps.MapDecorationType
import net.minecraft.world.level.saveddata.maps.MapDecoration
import net.minecraft.world.level.saveddata.maps.MapItemSavedData
import java.util.Optional

fun ClientboundMapItemDataPacketData.decorations(): MutableList<MapDecoration> {
    val list = mutableListOf<MapDecoration>()
    decorations.forEach { (type, x, y, rot, name) ->
        list.add(
            MapDecoration(
                MapDecorationType.convert(type).id,
                x,
                y,
                rot,
                Optional.of(PaperAdventure.asVanilla(name))
            )
        )
    }
    return list
}

fun ClientboundMapItemDataPacketData.colorPatch(): MapItemSavedData.MapPatch {
    return MapItemSavedData.MapPatch(
        colorPatch.startX,
        colorPatch.startY,
        colorPatch.width,
        colorPatch.height,
        colorPatch.mapColors.toByteArray()
    )
}