package net.crystopia.crystalshard.paper.dhl.types.variant

import org.bukkit.entity.Cow

data class CowVariant(
    var type: Cow.Variant,
    var spawnPrioritySelectors: Int
)
