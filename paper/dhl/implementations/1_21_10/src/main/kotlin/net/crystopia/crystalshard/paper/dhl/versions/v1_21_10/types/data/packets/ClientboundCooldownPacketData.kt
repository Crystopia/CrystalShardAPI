package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import org.bukkit.Material

data class ClientboundCooldownPacketData(
    var item: Material,
    var duration: Int
)
