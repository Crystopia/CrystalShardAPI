package net.crystopia.crystalshard.paper.dhl.shared.data.merchant

import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentExactPredicate
import net.minecraft.world.item.trading.ItemCost
import net.minecraft.world.item.trading.MerchantOffers
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.craftbukkit.inventory.CraftMerchantRecipe
import java.util.*
import kotlin.math.truncate

data class MerchantOffers(
    var offers: MutableList<net.crystopia.crystalshard.paper.dhl.shared.data.merchant.MerchantOffer>
)
