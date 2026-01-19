package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import org.bukkit.Material

data class ClientboundCooldownPacketData(
    var item: Material,
    var duration: Int
)
