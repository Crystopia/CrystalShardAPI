package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

data class MoveVehicleEvent(
    var x: Double,
    var y: Double,
    var z: Double,
    var yRot: Float,
    var xRot: Float,
    var onGround: Boolean
)