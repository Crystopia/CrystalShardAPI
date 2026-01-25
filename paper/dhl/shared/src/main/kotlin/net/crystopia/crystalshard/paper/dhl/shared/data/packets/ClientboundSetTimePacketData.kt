package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundSetTimePacketData(
    var gameTime: Long,
    var dayTime: Long,
    var tickDayTime: Boolean
)
