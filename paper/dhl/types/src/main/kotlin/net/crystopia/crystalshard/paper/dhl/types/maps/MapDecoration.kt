package net.crystopia.crystalshard.paper.dhl.types.maps

import net.kyori.adventure.text.Component

data class MapDecoration(
    var type: net.crystopia.crystalshard.dhl.shared.enums.maps.MapDecorationType,
    var x: Byte,
    var y: Byte,
    var rot: Byte,
    var name: Component
)
