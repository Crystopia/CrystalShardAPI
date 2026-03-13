package net.crystopia.crystalshard.paper.dhl.shared.data.variant

import org.bukkit.entity.Cow
import org.bukkit.entity.Frog

data class FrogVariant(
    var type: Frog.Variant,
    var spawnPrioritySelectors: Int
)
