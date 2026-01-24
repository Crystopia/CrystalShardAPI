package net.crystopia.crystalshard.paper.dhl.shared.data.maps

import net.crystopia.crystalshard.paper.dhl.shared.enums.maps.MapDecorationType
import net.kyori.adventure.text.Component

data class MapDecoration(
    var type: MapDecorationType,
    var x: Byte,
    var y: Byte,
    var rot: Byte,
    var name: Component
)
