package net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.types.RecipeEntry
import net.minecraft.core.NonNullList
import net.minecraft.world.inventory.RecipeBookType
import net.minecraft.world.item.crafting.CookingBookCategory
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.ShapedRecipePattern
import org.bukkit.craftbukkit.inventory.*
import org.bukkit.inventory.*
import org.bukkit.inventory.recipe.CraftingBookCategory
import kotlin.experimental.or

fun RecipeEntryBuilder.PAPER_1_21_1(
    recipe: net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry,
): RecipeEntry {
    return RecipeEntry(
        flags = (recipe.highlight or recipe.showNotification),
        id = NamespacedKey(recipe.id.namespace, recipe.id.key),
        recipe = recipe.PAPER_1_21_1()
    )
}

private fun net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry.PAPER_1_21_1(): net.minecraft.world.item.crafting.Recipe<*> {
    return when (val recipe = this.recipe) {
        is ShapedRecipe -> {
            val bukkit = CraftShapedRecipe.fromBukkitRecipe(recipe)

            val category = when ((this.recipe as ShapedRecipe).category) {
                org.bukkit.inventory.recipe.CraftingBookCategory.MISC -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.REDSTONE -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.REDSTONE
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.BUILDING -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.BUILDING
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.EQUIPMENT -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.EQUIPMENT
                }
            }

            net.minecraft.world.item.crafting.ShapedRecipe(
                this.group,
                category,
                ShapedRecipePattern.of(
                    bukkit.choiceMap.map { Pair(it.key, CraftRecipe.toIngredient(it.value, false)) }.toMap(),
                    *bukkit.shape
                ),
                CraftItemStack.asNMSCopy(bukkit.result),
            )
        }

        is ShapelessRecipe -> {
            val bukkit = CraftShapelessRecipe.fromBukkitRecipe(recipe)
            val list = NonNullList.create<Ingredient>()
            bukkit.choiceList.forEach { list.add(CraftRecipe.toIngredient(it, false)) }

            val category = when ((this.recipe as ShapelessRecipe).category) {
                org.bukkit.inventory.recipe.CraftingBookCategory.MISC -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.REDSTONE -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.REDSTONE
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.BUILDING -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.BUILDING
                }

                org.bukkit.inventory.recipe.CraftingBookCategory.EQUIPMENT -> {
                    net.minecraft.world.item.crafting.CraftingBookCategory.EQUIPMENT
                }
            }

            net.minecraft.world.item.crafting.ShapelessRecipe(
                this.group,
                category,
                CraftItemStack.asNMSCopy(bukkit.result),
                list
            )
        }

        is FurnaceRecipe -> {
            val bukkit = CraftFurnaceRecipe.fromBukkitRecipe(recipe)

            val category = when ((this.recipe as org.bukkit.inventory.FurnaceRecipe).category) {
                org.bukkit.inventory.recipe.CookingBookCategory.MISC -> {
                    CookingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CookingBookCategory.FOOD -> {
                    CookingBookCategory.FOOD
                }

                org.bukkit.inventory.recipe.CookingBookCategory.BLOCKS -> {
                    CookingBookCategory.BLOCKS
                }
            }

            net.minecraft.world.item.crafting.SmeltingRecipe(
                this.group,
                category,
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is BlastingRecipe -> {
            val bukkit = CraftBlastingRecipe.fromBukkitRecipe(recipe)

            val category = when ((this.recipe as org.bukkit.inventory.BlastingRecipe).category) {
                org.bukkit.inventory.recipe.CookingBookCategory.MISC -> {
                    CookingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CookingBookCategory.FOOD -> {
                    CookingBookCategory.FOOD
                }

                org.bukkit.inventory.recipe.CookingBookCategory.BLOCKS -> {
                    CookingBookCategory.BLOCKS
                }
            }

            net.minecraft.world.item.crafting.BlastingRecipe(
                this.group,
                category,
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is SmokingRecipe -> {
            val bukkit = CraftSmokingRecipe.fromBukkitRecipe(recipe)

            val category = when ((this.recipe as org.bukkit.inventory.SmokingRecipe).category) {
                org.bukkit.inventory.recipe.CookingBookCategory.MISC -> {
                    CookingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CookingBookCategory.FOOD -> {
                    CookingBookCategory.FOOD
                }

                org.bukkit.inventory.recipe.CookingBookCategory.BLOCKS -> {
                    CookingBookCategory.BLOCKS
                }
            }

            net.minecraft.world.item.crafting.SmokingRecipe(
                this.group,
                category,
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is CampfireRecipe -> {
            val bukkit = CraftCampfireRecipe.fromBukkitRecipe(recipe)

            val category = when ((this.recipe as CampfireRecipe).category) {
                org.bukkit.inventory.recipe.CookingBookCategory.MISC -> {
                    CookingBookCategory.MISC
                }

                org.bukkit.inventory.recipe.CookingBookCategory.FOOD -> {
                    CookingBookCategory.FOOD
                }

                org.bukkit.inventory.recipe.CookingBookCategory.BLOCKS -> {
                    CookingBookCategory.BLOCKS
                }
            }

            net.minecraft.world.item.crafting.CampfireCookingRecipe(
                this.group,
                category,
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is StonecuttingRecipe -> {
            val bukkit = CraftStonecuttingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.StonecutterRecipe(
                this.group,
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
            )
        }

        is SmithingTransformRecipe -> {
            val bukkit = CraftSmithingTransformRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmithingTransformRecipe(
                CraftRecipe.toIngredient(bukkit.template, false),
                CraftRecipe.toIngredient(bukkit.base, false),
                CraftRecipe.toIngredient(bukkit.addition, false),
                CraftItemStack.asNMSCopy(bukkit.result)
            )
        }

        is SmithingTrimRecipe -> {
            val bukkit = CraftSmithingTrimRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmithingTrimRecipe(
                CraftRecipe.toIngredient(bukkit.template, false),
                CraftRecipe.toIngredient(bukkit.base, false),
                CraftRecipe.toIngredient(bukkit.addition, false),
            )
        }


        else -> throw IllegalArgumentException("Unbekannter Recipe-Typ: ${recipe::class.simpleName}")
    }
}