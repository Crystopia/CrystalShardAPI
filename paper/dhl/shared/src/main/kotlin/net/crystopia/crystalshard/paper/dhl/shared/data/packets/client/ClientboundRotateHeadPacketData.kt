package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

data class ClientboundRotateHeadPacketData(
    val entity: Int,
    val yaw: Float
)