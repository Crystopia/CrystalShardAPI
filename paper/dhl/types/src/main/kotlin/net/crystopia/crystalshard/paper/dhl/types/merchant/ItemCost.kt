package net.crystopia.crystalshard.paper.dhl.types.merchant

import org.bukkit.inventory.ItemStack

data class ItemCost(
    var itemStack: ItemStack,
    var count: Int
)
