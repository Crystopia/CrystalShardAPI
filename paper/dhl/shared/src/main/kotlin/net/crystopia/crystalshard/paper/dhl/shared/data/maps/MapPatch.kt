package net.crystopia.crystalshard.paper.dhl.shared.data.maps

data class MapPatch(
    var startX: Int,
    var startY: Int,
    var width: Int,
    var height: Int,
    var mapColors: MutableList<Byte>
)
