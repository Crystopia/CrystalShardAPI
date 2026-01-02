package net.crystopia.crystalshard.shared.data.packets

import org.bukkit.Location

data class ClientboundTeleportEntityPacketData(
    val entityId: Int,
    val location: Location
)
