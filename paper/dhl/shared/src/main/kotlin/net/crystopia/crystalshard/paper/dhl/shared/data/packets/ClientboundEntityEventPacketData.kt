package net.crystopia.crystalshard.paper.dhl.shared.data.packets

data class ClientboundEntityEventPacketData(
    var entityId: Int,
    var status: Byte
)