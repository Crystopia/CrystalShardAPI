package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundContainerSetDataPacketData(
    var id :Int,
    var property: Short,
    var value: Short
)
