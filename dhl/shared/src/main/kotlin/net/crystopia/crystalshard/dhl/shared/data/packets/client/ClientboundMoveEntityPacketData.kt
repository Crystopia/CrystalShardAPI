package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.minecraft.world.entity.Entity

data class ClientboundMoveEntityPacketData(
    var mode: EntityMoveMode,
    var entity: Entity,
    var xa: Short,
    var ya: Short,
    var za: Short,
    var yRot: Byte,
    var xRot: Byte,
    var onGround: Boolean,
    var hasRot: Boolean,
    var hasPos: Boolean
)
