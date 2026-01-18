package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundEntityEventPacketData(
    var entity: Int,
    var status: Byte
)