package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.entity.Entity

data class ClientboundAnimatePacketData(
    var entity: Entity, var animationId: Int
)