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
)
