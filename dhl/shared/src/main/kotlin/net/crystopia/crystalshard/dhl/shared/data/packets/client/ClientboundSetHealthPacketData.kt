package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundSetHealthPacketData(
    var health: Float,
    var food: Int,
    var saturation: Float
)