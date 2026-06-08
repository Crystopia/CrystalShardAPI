package net.crystopia.crystalshard.paper.dhl.extension

import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.minecraft.world.item.crafting.RecipeBookCategories
import org.bukkit.Keyed
import org.bukkit.inventory.*
import org.bukkit.inventory.recipe.CookingBookCategory
import org.bukkit.inventory.recipe.CraftingBookCategory

fun Recipe.toDhlRecipeEntry(
    showNotification: Boolean,
    highlight: Boolean,
): RecipeEntry {
    val recipeKey = if (this is Keyed) this.key
    else throw IllegalArgumentException("Recipe is not Keyed: $this")

    val group: String = when (this) {
        is CraftingRecipe -> this.group
        is FurnaceRecipe -> this.group
        is BlastingRecipe -> this.group
        is SmokingRecipe -> this.group
        is CampfireRecipe -> this.group
        is StonecuttingRecipe -> this.group
        is SmithingRecipe -> ""
        else -> ""
    }

    return RecipeEntry(
        id = recipeKey,
        recipe = this,
        showNotification = if (showNotification) 0x01 else 0x00,
        highlight = if (highlight) 0x02 else 0x00,
        group = group,
    )
}