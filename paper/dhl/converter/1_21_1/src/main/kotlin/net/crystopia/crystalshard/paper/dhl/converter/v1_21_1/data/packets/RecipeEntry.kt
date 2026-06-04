package net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.types.RecipeEntry
import net.minecraft.core.NonNullList
import net.minecraft.world.item.crafting.CookingBookCategory
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.ShapedRecipePattern
import org.bukkit.craftbukkit.inventory.*
import org.bukkit.inventory.*
import kotlin.experimental.or

fun RecipeEntryBuilder.PAPER_1_21_1(
    recipe: net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry,
): RecipeEntry {
    return RecipeEntry(
        flags = (recipe.highlight or recipe.showNotification), // TODO: TEST THIS!!
        id = NamespacedKey(recipe.id.namespace, recipe.id.key),
        recipe = recipe.recipe_1_21_1()
    )
}

fun net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry.recipe_1_21_1(): net.minecraft.world.item.crafting.Recipe<*> {
    return when (val recipe = this.recipe) {
        is ShapedRecipe -> {
            val bukkit = CraftShapedRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.ShapedRecipe(
                bukkit.group,
                net.minecraft.world.item.crafting.CraftingBookCategory.valueOf(bukkit.category.name),
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
            net.minecraft.world.item.crafting.ShapelessRecipe(
                bukkit.group,
                net.minecraft.world.item.crafting.CraftingBookCategory.valueOf(bukkit.category.name),
                CraftItemStack.asNMSCopy(bukkit.result),
                list
            )
        }

        is FurnaceRecipe -> {
            val bukkit = CraftFurnaceRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmeltingRecipe(
                bukkit.group,
                net.minecraft.world.item.crafting.CookingBookCategory.valueOf(bukkit.category.name),
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is BlastingRecipe -> {
            val bukkit = CraftBlastingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.BlastingRecipe(
                bukkit.group,
                net.minecraft.world.item.crafting.CookingBookCategory.valueOf(bukkit.category.name),
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is SmokingRecipe -> {
            val bukkit = CraftSmokingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmokingRecipe(
                bukkit.group,
                CookingBookCategory.valueOf(
                    this.category.name
                ),
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is CampfireRecipe -> {
            val bukkit = CraftCampfireRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.CampfireCookingRecipe(
                bukkit.group,
                net.minecraft.world.item.crafting.CookingBookCategory.valueOf(this.category.name),
                CraftRecipe.toIngredient(bukkit.inputChoice, false),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is StonecuttingRecipe -> {
            val bukkit = CraftStonecuttingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.StonecutterRecipe(
                bukkit.group,
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