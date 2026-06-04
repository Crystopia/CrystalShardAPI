package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.paper.dhl.types.recipes.display
import net.crystopia.crystalshard.paper.dhl.types.recipes.ingredients
import net.crystopia.crystalshard.paper.dhl.types.recipes.recipe
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId
import java.util.*
import kotlin.experimental.or

fun RecipeEntryBuilder.PAPER_1_21_11(
    recipe: net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry,
): RecipeEntry {
    return net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry(
            flags = (recipe.highlight or recipe.showNotification), // TODO: TEST THIS!!
            recipeDisplay = RecipeDisplayEntry(
                RecipeDisplayId(recipe.order),
                recipe.display(),
                OptionalInt.of(recipe.group),
                RecipeBookCategories.convert(
                    recipe.category
                ).category,
                Optional.ofNullable(
                    recipe.ingredients()
                )
            ),
            id = NamespacedKey(recipe.id.namespace, recipe.id.key),
            recipe = recipe.recipe()
        )
}