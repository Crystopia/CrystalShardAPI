package net.crystopia.crystalshard.paper.dhl.types.variant

import org.bukkit.entity.Cat

data class CatVariant(
    var type : Cat.Type,
    var spawnPrioritySelectors: Int
)
