package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import org.bukkit.Location

data class ClientboundTeleportEntityPacketData(
    var entityId: Int,
    var location: Location,
    var onGround : Boolean
)
