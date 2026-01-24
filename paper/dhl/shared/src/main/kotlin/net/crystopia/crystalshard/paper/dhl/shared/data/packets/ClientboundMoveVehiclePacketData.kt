package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.world.Vec3

data class ClientboundMoveVehiclePacketData(
    var position: Vec3,
    var yRot: Float,
    var xRot: Float
)
