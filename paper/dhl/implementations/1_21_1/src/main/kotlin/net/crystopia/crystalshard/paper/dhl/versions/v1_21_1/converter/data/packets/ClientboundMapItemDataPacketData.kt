package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.maps.MapDecorationType
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

fun ClientboundMapItemDataPacketData.decorations(): MutableList<net.minecraft.world.level.saveddata.maps.MapDecoration> {
    val list = mutableListOf<net.minecraft.world.level.saveddata.maps.MapDecoration>()
    decorations.forEach { (type, x, y, rot, name) ->
        list.add(
            net.minecraft.world.level.saveddata.maps.MapDecoration(
                MapDecorationType.convert(type).id,
                x,
                y,
                rot,
                java.util.Optional.of(PaperAdventure.asVanilla(name))
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