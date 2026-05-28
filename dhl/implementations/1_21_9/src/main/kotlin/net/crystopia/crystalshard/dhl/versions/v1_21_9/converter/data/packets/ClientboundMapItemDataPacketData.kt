package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.enums.maps.MapDecorationType
import net.minecraft.world.level.saveddata.maps.MapDecoration
import net.minecraft.world.level.saveddata.maps.MapItemSavedData
import java.util.*

fun ClientboundMapItemDataPacketData.decorations(): MutableList<MapDecoration> {
    val list = mutableListOf<MapDecoration>()
    decorations.forEach { (type, x, y, rot, name) ->
        list.add(
            MapDecoration(
                MapDecorationType.convert(type).id,
                x,
                y,
                rot,
                Optional.of(name)
            )
        )
    }
    return list
}

fun ClientboundMapItemDataPacketData.colorPatch(): MapItemSavedData.MapPatch {
    return MapItemSavedData.MapPatch(
        colorPatch.startX,
        colorPatch.startZ,
        colorPatch.width,
        colorPatch.height,
        colorPatch.mapColors.toByteArray()
    )
}