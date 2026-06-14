package net.crystopia.crystalshard.paper.dhl.converter.v1_21_10

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.dhl.types.recipes.ingredients
import net.minecraft.world.item.crafting.RecipeBookCategories
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId
import org.bukkit.inventory.*
import org.bukkit.inventory.recipe.CookingBookCategory
import java.util.*
import kotlin.experimental.or

fun RecipeEntryBuilder.PAPER_1_21_10(
    recipe: net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry,
): RecipeEntry {
    val category = when (recipe.recipe) {
        is org.bukkit.inventory.CraftingRecipe -> {
            when ((recipe.recipe as org.bukkit.inventory.CraftingRecipe).category) {
                org.bukkit.inventory.recipe.CraftingBookCategory.BUILDING -> {
                    RecipeBookCategories.CRAFTING_BUILDING_BLOCKS
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.REDSTONE -> {
                    RecipeBookCategories.CRAFTING_REDSTONE
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.EQUIPMENT -> {
                    RecipeBookCategories.CRAFTING_EQUIPMENT
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.MISC -> {
                    RecipeBookCategories.CRAFTING_MISC
                }
            }
        }

        is FurnaceRecipe -> {
            when ((recipe.recipe as org.bukkit.inventory.FurnaceRecipe).category) {
                CookingBookCategory.MISC -> {
                    RecipeBookCategories.FURNACE_MISC
                }

                CookingBookCategory.FOOD -> {
                    RecipeBookCategories.FURNACE_FOOD
                }

                CookingBookCategory.BLOCKS -> {
                    RecipeBookCategories.FURNACE_BLOCKS
                }
            }
        }

        is BlastingRecipe -> {
            when ((recipe.recipe as org.bukkit.inventory.BlastingRecipe).category) {
                CookingBookCategory.MISC -> {
                    RecipeBookCategories.BLAST_FURNACE_MISC
                }

                CookingBookCategory.FOOD -> {
                    RecipeBookCategories.BLAST_FURNACE_MISC
                }

                CookingBookCategory.BLOCKS -> {
                    RecipeBookCategories.BLAST_FURNACE_BLOCKS
                }
            }
        }

        is SmokingRecipe -> {
            when ((recipe.recipe as org.bukkit.inventory.SmokingRecipe).category) {
                CookingBookCategory.MISC -> {
                    RecipeBookCategories.SMOKER_FOOD
                }

                CookingBookCategory.FOOD -> {
                    RecipeBookCategories.SMOKER_FOOD
                }

                CookingBookCategory.BLOCKS -> {
                    RecipeBookCategories.SMOKER_FOOD
                }
            }
        }

        is CampfireRecipe -> {
            when ((recipe.recipe as CampfireRecipe).category) {
                CookingBookCategory.MISC -> {
                    RecipeBookCategories.CAMPFIRE
                }

                CookingBookCategory.FOOD -> {
                    RecipeBookCategories.CAMPFIRE
                }

                CookingBookCategory.BLOCKS -> {
                    RecipeBookCategories.CAMPFIRE
                }
            }
        }

        is StonecuttingRecipe -> RecipeBookCategories.STONECUTTER
        is SmithingTransformRecipe -> RecipeBookCategories.SMITHING
        is SmithingTrimRecipe -> RecipeBookCategories.SMITHING
        else -> RecipeBookCategories.CRAFTING_MISC
    }

    return RecipeEntry(
        flags = (recipe.highlight or recipe.showNotification),
        recipeDisplayEntry = RecipeDisplayEntry(
            RecipeDisplayId(recipe.hashCode()),
            recipe.display(),
            OptionalInt.of(recipe.group.hashCode()),
            category,
            recipe.ingredients()
        ), id = NamespacedKey(recipe.id.namespace, recipe.id.key), recipe = recipe.recipe()
    )
}