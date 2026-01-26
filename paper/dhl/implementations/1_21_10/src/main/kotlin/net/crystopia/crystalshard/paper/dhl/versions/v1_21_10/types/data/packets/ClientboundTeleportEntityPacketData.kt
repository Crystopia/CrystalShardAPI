package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import org.bukkit.Location

data class ClientboundTeleportEntityPacketData(
    var entityId: Int,
    var location: Location,
    var onGround : Boolean
)
