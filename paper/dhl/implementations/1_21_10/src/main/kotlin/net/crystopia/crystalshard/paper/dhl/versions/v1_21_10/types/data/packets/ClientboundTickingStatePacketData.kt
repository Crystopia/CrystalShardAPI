package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

data class ClientboundTickingStatePacketData(
    var tickRate: Float,
    var isFrozen: Boolean
)
