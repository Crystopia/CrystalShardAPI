package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.world.WorldBorder

data class ClientboundBorderPacketData(
    var border: WorldBorder
)
