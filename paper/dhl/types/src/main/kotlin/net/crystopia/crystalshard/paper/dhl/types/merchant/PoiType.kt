package net.crystopia.crystalshard.paper.dhl.types.merchant

import org.bukkit.Material

data class PoiType(
    var matchingStates: MutableSet<Material>,
    var maxTickets: Int,
    var validRange: Int
)
