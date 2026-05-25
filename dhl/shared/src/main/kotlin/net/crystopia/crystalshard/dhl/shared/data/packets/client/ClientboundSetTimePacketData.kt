package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundSetTimePacketData(
    var gameTime: Long,
    var dayTime: Long,
    var tickDayTime: Boolean
)
