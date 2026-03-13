package net.crystopia.crystalshard.paper.dhl.shared.data.variant

import org.bukkit.entity.Cow
import org.bukkit.entity.Frog
import org.bukkit.entity.Pig

data class PigVariant(
    var type: Pig.Variant,
    var spawnPrioritySelectors: Int
)
