package net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.recipes

import net.minecraft.world.item.crafting.RecipeBookCategories
import net.minecraft.world.item.crafting.RecipeBookCategory

enum class RecipeBookCategories(val category: RecipeBookCategory) {
    CRAFTING_BUILDING_BLOCKS(RecipeBookCategories.CRAFTING_BUILDING_BLOCKS),
    CRAFTING_REDSTONE(RecipeBookCategories.CRAFTING_REDSTONE),
    CRAFTING_EQUIPMENT(RecipeBookCategories.CRAFTING_EQUIPMENT),
    CRAFTING_MISC(RecipeBookCategories.CRAFTING_MISC),
    FURNACE_FOOD(RecipeBookCategories.FURNACE_FOOD),
    FURNACE_BLOCKS(RecipeBookCategories.FURNACE_BLOCKS),
    FURNACE_MISC(RecipeBookCategories.FURNACE_MISC),
    BLAST_FURNACE_BLOCKS(RecipeBookCategories.BLAST_FURNACE_BLOCKS),
    BLAST_FURNACE_MISC(RecipeBookCategories.BLAST_FURNACE_MISC),
    SMOKER_FOOD(RecipeBookCategories.SMOKER_FOOD),
    STONECUTTER(RecipeBookCategories.STONECUTTER),
    SMITHING(RecipeBookCategories.SMITHING),
    CAMPFIRE(RecipeBookCategories.CAMPFIRE);

    companion object {
        fun convert(type: net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeBookCategories): net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.recipes.RecipeBookCategories {
            return net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.recipes.RecipeBookCategories.valueOf(
                type.name
            )
        }
    }
}