package net.crystopia.crystalshard.paper.dhl.types.maps

data class MapPatch(
    var startX: Int,
    var startY: Int,
    var width: Int,
    var height: Int,
    var mapColors: MutableList<Byte>
)
