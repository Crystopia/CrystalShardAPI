package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.world.Vec3
import net.minecraft.world.entity.Entity

data class ClientboundMoveVehiclePacketData(
    var entity : Entity,
    var position: Vec3,
    var yRot: Float,
    var xRot: Float
)
