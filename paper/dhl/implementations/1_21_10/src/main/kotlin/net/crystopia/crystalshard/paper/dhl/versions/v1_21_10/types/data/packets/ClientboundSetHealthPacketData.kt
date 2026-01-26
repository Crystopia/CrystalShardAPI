package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

data class ClientboundSetHealthPacketData(
    var health: Float,
    var food: Int,
    var saturation: Float
)