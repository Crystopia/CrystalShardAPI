package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundAnimatePacketData(
    var entity: Entity, var animationId: Int
)