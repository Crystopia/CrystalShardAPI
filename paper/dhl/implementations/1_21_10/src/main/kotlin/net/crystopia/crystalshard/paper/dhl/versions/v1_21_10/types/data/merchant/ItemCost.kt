package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.merchant

import org.bukkit.inventory.ItemStack

data class ItemCost(
    var itemStack: ItemStack,
    var count: Int
)
