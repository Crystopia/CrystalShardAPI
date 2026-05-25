package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.entities.PositionMoveRotation

data class ClientboundEntityPositionSyncPacketData(
    var entityId: Int,
    var values: PositionMoveRotation,
    var onGround: Boolean
)
