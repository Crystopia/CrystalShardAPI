package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundEntityEventPacketData(
    var entity: Entity,
    var status: Byte
)