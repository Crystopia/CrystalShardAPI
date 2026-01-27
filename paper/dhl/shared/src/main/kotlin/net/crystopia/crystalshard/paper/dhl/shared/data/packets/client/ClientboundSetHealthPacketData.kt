package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

data class ClientboundSetHealthPacketData(
    var health: Float,
    var food: Int,
    var saturation: Float
)