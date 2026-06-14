package net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.types.RecipeEntry
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