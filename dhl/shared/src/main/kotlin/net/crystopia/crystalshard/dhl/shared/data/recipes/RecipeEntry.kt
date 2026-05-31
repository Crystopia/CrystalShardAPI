package net.crystopia.crystalshard.dhl.shared.data.recipes

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry

data class RecipeEntry(
    var id: NamespacedKey,
    var flags: Byte,
    var recipe: Recipe<*>,
    var recipeDisplay: RecipeDisplayEntry
)
