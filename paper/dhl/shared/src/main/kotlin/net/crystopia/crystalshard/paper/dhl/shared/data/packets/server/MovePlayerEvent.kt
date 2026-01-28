package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

data class MovePlayerEvent(
    var x: Double,
    var y: Double,
    var z: Double,
    var yRot: Float,
    var xRot: Float,
    var onGround: Boolean,
    var horizontalCollision: Boolean?,
    var hasPos: Boolean,
    var hasRot: Boolean
)
