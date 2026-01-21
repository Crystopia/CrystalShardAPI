package net.crystopia.crystalshard.paper.dhl.shared.data.merchant

import org.bukkit.inventory.ItemStack

data class MerchantOffer(
    var baseCost: ItemCost,
    var optionalCost: ItemCost?,
    var result: ItemStack,
    var uses: Int,
    var maxUses: Int,
    var experience: Int,
    var priceMultiplier: Float,
    var demand: Int,
    var specialPrice: Int,
    var ignoreDiscounts: Boolean,
    var experienceReward: Boolean
)
