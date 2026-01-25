package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundTickingStatePacketData(
    var tickRate: Float,
    var isFrozen: Boolean
)
