package net.crystopia.crystalshard.dhl.shared.data.maps

import net.crystopia.crystalshard.dhl.shared.enums.maps.MapDecorationType
import net.minecraft.network.chat.Component

data class MapDecoration(
    var type: MapDecorationType,
    var x: Byte,
    var y: Byte,
    var rot: Byte,
    var name: Component
)
