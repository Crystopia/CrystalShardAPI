package net.crystopia.crystalshard.dhl.shared.data.merchant

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.trading.ItemCost

data class MerchantOffer(
    var baseCost: net.minecraft.world.item.trading.ItemCost,
    var optionalCost: ItemCost?,
    var result: ItemStack,
    var uses: Int,
    var maxUses: Int,
    var experience: Int,
    var priceMultiplier: Float,
    var demand: Int,
    var specialPriceDiff: Int,
    var specialPrice: Int,
    var ignoreDiscounts: Boolean,
    var experienceReward: Boolean
)
