package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.merchant

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentExactPredicate
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraft.world.item.trading.MerchantOffers
import java.util.*

fun net.crystopia.crystalshard.dhl.shared.data.merchant.MerchantOffers.build(): MerchantOffers {
    val merchantOffers = MerchantOffers()
    offers.forEach { offer ->
        val mcCostA = offer.baseCost.itemStack
        var bCost: ItemCost? = null
        if (offer.optionalCost != null) {
            requireNotNull(offer.optionalCost)

            val mcCostB = offer.optionalCost!!.itemStack
            bCost = ItemCost(
                Holder.direct(mcCostB.item),
                offer.baseCost.count,
                DataComponentExactPredicate.allOf(mcCostB.components),
                mcCostB
            )
        }

        val data = MerchantOffer(
            ItemCost(
                Holder.direct(mcCostA.item),
                offer.baseCost.count,
                DataComponentExactPredicate.allOf(mcCostA.components),
                mcCostA
            ),
            Optional.ofNullable(bCost),
            offer.result,
            offer.uses,
            offer.maxUses,
            offer.experience,
            offer.priceMultiplier,
            offer.demand,
        )

        merchantOffers.add(
            data
        )
    }
    return merchantOffers
}