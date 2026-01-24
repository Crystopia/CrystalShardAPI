package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation

data class ClientboundEntityPositionSyncPacketData(
    var entityId: Int,
    var values: PositionMoveRotation,
    var onGround: Boolean
)
