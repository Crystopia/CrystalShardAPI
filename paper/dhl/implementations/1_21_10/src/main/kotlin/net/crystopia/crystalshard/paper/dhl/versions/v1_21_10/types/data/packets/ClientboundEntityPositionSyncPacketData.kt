package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.PositionMoveRotation

data class ClientboundEntityPositionSyncPacketData(
    var entityId: Int,
    var values: PositionMoveRotation,
    var onGround: Boolean
)
