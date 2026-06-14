package net.crystopia.crystalshard.paper.dhl.types.recipes

import net.minecraft.world.item.crafting.CampfireCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import org.bukkit.NamespacedKey
import org.bukkit.craftbukkit.inventory.CraftRecipe
import org.bukkit.inventory.*
import java.util.*

data class RecipeEntry(
    var id: NamespacedKey,
    var showNotification: Byte = 0x01,
    var highlight: Byte = 0x02,
    var group: String,
    var recipe: Recipe,
)

fun RecipeEntry.ingredients(): Optional<MutableList<Ingredient>> {
    return when (val cast = recipe) {
        is ShapedRecipe -> {
            val shape = cast.shape
            val width = shape.maxOf { it.length }
            Optional.of(shape.flatMap { row ->
                row.padEnd(width).mapNotNull { char ->
                    val choice = cast.choiceMap[char]
                    if (choice == null) null
                    else CraftRecipe.toIngredient(choice, true)
                }
            }.toMutableList())
        }
        is ShapelessRecipe -> Optional.of(cast.choiceList.map { choice ->
            CraftRecipe.toIngredient(choice, true)
        }.toMutableList())
        is FurnaceRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.inputChoice, true)
        ))
        is BlastingRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.inputChoice, true)
        ))
        is SmokingRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.inputChoice, true)
        ))
        is CampfireCookingRecipe -> Optional.of(mutableListOf(
            cast.input()
        ))
        is StonecuttingRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.inputChoice, true)
        ))
        is SmithingTransformRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.template, true),
            CraftRecipe.toIngredient(cast.base, true),
            CraftRecipe.toIngredient(cast.addition, true)
        ))
        is SmithingTrimRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.template, true),
            CraftRecipe.toIngredient(cast.base, true),
            CraftRecipe.toIngredient(cast.addition, true)
        ))
        is TransmuteRecipe -> Optional.of(mutableListOf(
            CraftRecipe.toIngredient(cast.input, true),
            CraftRecipe.toIngredient(cast.material, true)
        ))
        is ComplexRecipe, is MerchantRecipe -> Optional.empty()
        else -> Optional.empty()
    }
}