package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode

data class ClientboundMoveEntityPacketData(
    var mode: EntityMoveMode,
    var entityId: Int,
    var xa: Short,
    var ya: Short,
    var za: Short,
    var yRot: Byte,
    var xRot: Byte,
    var onGround: Boolean,
    var hasRot: Boolean,
    var hasPos: Boolean
)
