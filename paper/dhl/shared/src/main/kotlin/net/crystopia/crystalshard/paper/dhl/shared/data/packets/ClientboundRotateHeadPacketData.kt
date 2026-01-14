package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundRotateHeadPacketData(
    val entity: Entity,
    val yaw: Float
)