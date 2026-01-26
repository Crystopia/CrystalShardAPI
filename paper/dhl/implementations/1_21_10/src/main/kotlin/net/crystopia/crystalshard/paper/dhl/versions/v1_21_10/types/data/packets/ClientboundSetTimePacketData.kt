package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

data class ClientboundSetTimePacketData(
    var gameTime: Long,
    var dayTime: Long,
    var tickDayTime: Boolean
)
