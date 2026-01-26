package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.maps

data class MapPatch(
    var startX: Int,
    var startY: Int,
    var width: Int,
    var height: Int,
    var mapColors: MutableList<Byte>
)
