package net.crystopia.crystalshard.paper.dhl.extension

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice

fun RecipeChoice.forDhlToItemStacks(): List<ItemStack> = when (this) {
    is RecipeChoice.ExactChoice -> this.choices
    is RecipeChoice.MaterialChoice -> this.choices.map { ItemStack(it) }
    else -> emptyList()
}