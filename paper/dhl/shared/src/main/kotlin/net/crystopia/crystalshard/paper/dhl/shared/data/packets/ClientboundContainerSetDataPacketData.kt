package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundContainerSetDataPacketData(
    var id :Int,
    var property: Short,
    var value: Short
)
