package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3

data class PositionMoveRotation(
    var position: Vec3,
    var deltaMovement: Vec3,
    var yRot: Float,
    var xRot: Float
)
