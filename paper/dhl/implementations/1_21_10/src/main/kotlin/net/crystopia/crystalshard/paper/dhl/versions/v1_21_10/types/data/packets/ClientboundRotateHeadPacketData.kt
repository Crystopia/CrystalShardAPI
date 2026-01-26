package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundRotateHeadPacketData(
    val entity: Int,
    val yaw: Float
)