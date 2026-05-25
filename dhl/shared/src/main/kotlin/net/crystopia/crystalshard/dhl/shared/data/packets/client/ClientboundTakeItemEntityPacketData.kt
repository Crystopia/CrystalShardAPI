package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundTakeItemEntityPacketData(
    var itemId: Int,
    var playerId: Int,
    var amount: Int
)
