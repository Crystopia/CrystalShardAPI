package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

data class ClientboundSetTimePacketData(
    var gameTime: Long,
    var dayTime: Long,
    var tickDayTime: Boolean
)
