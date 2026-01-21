package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.world.WorldBorder

data class ClientboundBorderPacketData(
    var border: WorldBorder
)
