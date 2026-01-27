package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.Material

data class ClientboundCooldownPacketData(
    var item: Material,
    var duration: Int
)
