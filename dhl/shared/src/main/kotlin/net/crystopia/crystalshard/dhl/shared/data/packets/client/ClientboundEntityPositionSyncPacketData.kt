package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.entities.PositionMoveRotation
import net.minecraft.world.entity.Entity

data class ClientboundEntityPositionSyncPacketData(
    var entity: Entity,
    var values: PositionMoveRotation,
    var onGround: Boolean
)
