package net.crystopia.crystalshard.paper.dhl.extension

import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
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

    val category: RecipeBookCategories = when (this) {
        /*
        TODO
         is TransmuteRecipe -> when (this.category) {
                    CraftingBookCategory.BUILDING -> RecipeBookCategories.CRAFTING_BUILDING_BLOCKS
                    CraftingBookCategory.REDSTONE -> RecipeBookCategories.CRAFTING_REDSTONE
                    CraftingBookCategory.EQUIPMENT -> RecipeBookCategories.CRAFTING_EQUIPMENT
                    CraftingBookCategory.MISC -> RecipeBookCategories.CRAFTING_MISC
                }
         */

        is ShapedRecipe -> when (this.category) {
            CraftingBookCategory.BUILDING -> RecipeBookCategories.CRAFTING_BUILDING_BLOCKS
            CraftingBookCategory.REDSTONE -> RecipeBookCategories.CRAFTING_REDSTONE
            CraftingBookCategory.EQUIPMENT -> RecipeBookCategories.CRAFTING_EQUIPMENT
            CraftingBookCategory.MISC -> RecipeBookCategories.CRAFTING_MISC
        }

        is ShapelessRecipe -> when (this.category) {
            CraftingBookCategory.BUILDING -> RecipeBookCategories.CRAFTING_BUILDING_BLOCKS
            CraftingBookCategory.REDSTONE -> RecipeBookCategories.CRAFTING_REDSTONE
            CraftingBookCategory.EQUIPMENT -> RecipeBookCategories.CRAFTING_EQUIPMENT
            CraftingBookCategory.MISC -> RecipeBookCategories.CRAFTING_MISC
        }


        is FurnaceRecipe -> when (this.category) {
            CookingBookCategory.FOOD -> RecipeBookCategories.FOOD
            CookingBookCategory.BLOCKS -> RecipeBookCategories.BLOCKS
            CookingBookCategory.MISC -> RecipeBookCategories.MISC
        }

        is BlastingRecipe -> when (this.category) {
            CookingBookCategory.FOOD -> RecipeBookCategories.FOOD
            CookingBookCategory.BLOCKS -> RecipeBookCategories.BLOCKS
            CookingBookCategory.MISC -> RecipeBookCategories.MISC
        }

        is SmokingRecipe -> when (this.category) {
            CookingBookCategory.FOOD -> RecipeBookCategories.FOOD
            CookingBookCategory.BLOCKS -> RecipeBookCategories.BLOCKS
            CookingBookCategory.MISC -> RecipeBookCategories.MISC
        }

        is CampfireRecipe -> when (this.category) {
            CookingBookCategory.FOOD -> RecipeBookCategories.FOOD
            CookingBookCategory.BLOCKS -> RecipeBookCategories.BLOCKS
            CookingBookCategory.MISC -> RecipeBookCategories.MISC
        }

        is StonecuttingRecipe -> RecipeBookCategories.STONECUTTER
        is SmithingTransformRecipe -> RecipeBookCategories.SMITHING
        is SmithingTrimRecipe -> RecipeBookCategories.SMITHING
        else -> RecipeBookCategories.CRAFTING_MISC
    }

    return RecipeEntry(
        id = recipeKey,
        recipe = this,
        showNotification = if (showNotification) 0x01 else 0x00,
        highlight = if (highlight) 0x02 else 0x00,
        group = group,
        category = category,
    )
}