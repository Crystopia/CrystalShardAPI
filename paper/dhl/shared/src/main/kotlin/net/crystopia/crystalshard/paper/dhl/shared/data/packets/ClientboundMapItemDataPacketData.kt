package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapPatch
import net.minecraft.world.level.saveddata.maps.MapItemSavedData

data class ClientboundMapItemDataPacketData(
    var mapId: Int,
    var scale: Byte,
    var locked: Boolean,
    var decorations: MutableList<MapDecoration>,
    var colorPatch: MapPatch
) {
    fun decorations(): MutableList<net.minecraft.world.level.saveddata.maps.MapDecoration> {
        val list = mutableListOf<net.minecraft.world.level.saveddata.maps.MapDecoration>()
        decorations.forEach { (type, x, y, rot, name) ->
            list.add(
                net.minecraft.world.level.saveddata.maps.MapDecoration(
                    type.id,
                    x,
                    y,
                    rot,
                    java.util.Optional.of(PaperAdventure.asVanilla(name))
                )
            )
        }
        return list
    }

    fun colorPatch(): MapItemSavedData.MapPatch {
        return MapItemSavedData.MapPatch(
            colorPatch.startX,
            colorPatch.startY,
            colorPatch.width,
            colorPatch.height,
            colorPatch.mapColors.toByteArray()
        )
    }
}
