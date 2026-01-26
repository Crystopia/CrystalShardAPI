package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3

data class MinecartStep(
    var position: Vec3,
    var movement: Vec3,
    var yRot: Float,
    var xRot: Float,
    var weight: Float
)