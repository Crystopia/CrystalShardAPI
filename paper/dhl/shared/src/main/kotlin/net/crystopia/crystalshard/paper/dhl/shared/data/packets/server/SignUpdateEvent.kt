package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

data class SignUpdateEvent(
    var x: Int,
    var y: Int,
    var z: Int,
    var lines: MutableList<String>,
    var isFrontText: Boolean
)
