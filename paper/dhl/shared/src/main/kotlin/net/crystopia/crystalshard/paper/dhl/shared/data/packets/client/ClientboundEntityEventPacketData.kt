package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.World
import org.bukkit.entity.Entity

data class ClientboundEntityEventPacketData(
    var entity: Entity,
    var status: Byte,
    var world: World
)