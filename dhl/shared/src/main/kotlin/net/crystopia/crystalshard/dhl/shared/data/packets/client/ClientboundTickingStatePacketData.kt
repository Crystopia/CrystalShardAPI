package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundTickingStatePacketData(
    var tickRate: Float,
    var isFrozen: Boolean
)
