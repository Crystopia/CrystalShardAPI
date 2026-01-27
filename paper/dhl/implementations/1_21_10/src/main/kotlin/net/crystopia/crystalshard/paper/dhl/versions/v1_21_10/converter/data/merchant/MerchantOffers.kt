package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.merchant

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentExactPredicate
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraft.world.item.trading.MerchantOffers
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.craftbukkit.inventory.CraftMerchantRecipe
import java.util.Optional

fun net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffers.build(): MerchantOffers {
    val merchantOffers = MerchantOffers()
    offers.forEach { offer ->
        val mcCostA = CraftItemStack.asNMSCopy(offer.baseCost.itemStack)
        var bCost: ItemCost? = null
        if (offer.optionalCost != null) {
            requireNotNull(offer.optionalCost)

            val mcCostB = CraftItemStack.asNMSCopy(offer.optionalCost!!.itemStack)
            bCost = ItemCost(
                Holder.direct(mcCostB.item),
                offer.baseCost.count,
                DataComponentExactPredicate.allOf(mcCostB.components),
                mcCostB
            )
        }

        val bukkit = CraftMerchantRecipe(
            offer.result,
            offer.uses,
            offer.maxUses,
            offer.experienceReward,
            offer.experience,
            offer.priceMultiplier,
            offer.demand,
            offer.specialPrice,
            offer.ignoreDiscounts
        )

        val data = MerchantOffer(
            ItemCost(
                Holder.direct(mcCostA.item),
                offer.baseCost.count,
                DataComponentExactPredicate.allOf(mcCostA.components),
                mcCostA
            ),
            Optional.ofNullable(bCost),
            (CraftItemStack.asNMSCopy(offer.result)),
            offer.uses,
            offer.maxUses,
            offer.experience,
            offer.priceMultiplier,
            offer.demand,
            offer.ignoreDiscounts,
            bukkit
        )

        merchantOffers.add(
            data
        )
    }
    return merchantOffers
}