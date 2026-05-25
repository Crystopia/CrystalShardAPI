package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.entity.Entity

data class ClientboundRotateHeadPacketData(
    val entity: Entity,
    val yaw: Float
)