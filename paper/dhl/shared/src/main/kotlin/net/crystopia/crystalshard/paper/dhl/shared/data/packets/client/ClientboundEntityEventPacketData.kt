package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.World

data class ClientboundEntityEventPacketData(
    var entityId: Int,
    var status: Byte,
    var world: World
)