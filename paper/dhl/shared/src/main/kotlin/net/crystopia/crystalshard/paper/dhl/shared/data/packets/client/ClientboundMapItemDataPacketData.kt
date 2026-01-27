package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.paper.dhl.shared.data.maps.MapPatch

data class ClientboundMapItemDataPacketData(
    var mapId: Int,
    var scale: Byte,
    var locked: Boolean,
    var decorations: MutableList<MapDecoration>,
    var colorPatch: MapPatch
)
