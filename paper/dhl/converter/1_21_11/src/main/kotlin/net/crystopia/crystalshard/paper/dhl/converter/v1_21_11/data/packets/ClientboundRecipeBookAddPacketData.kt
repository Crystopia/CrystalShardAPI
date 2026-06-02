package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets

import net.crystopia.crystalshard.dhl.shared.builder.RecipeEntryBuilder
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.paper.dhl.types.recipes.display
import net.crystopia.crystalshard.paper.dhl.types.recipes.ingredients
import net.crystopia.crystalshard.paper.dhl.types.recipes.recipe
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId
import java.util.*
import kotlin.experimental.or

fun RecipeEntryBuilder.PAPER_1_21_11(
    recipes: MutableList<net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry>,
    replace: Boolean,
): ClientboundRecipeBookAddPacketData {
    return ClientboundRecipeBookAddPacketData(
        recipeEntries = recipes.map { choice ->
            net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry(
                flags = (choice.highlight or choice.showNotification), // TODO: TEST THIS!!
                recipeDisplay = RecipeDisplayEntry(
                    RecipeDisplayId(choice.order),
                    choice.display(),
                    OptionalInt.of(choice.group),
                    RecipeBookCategories.convert(
                        choice.category
                    ).category,
                    Optional.ofNullable(
                        choice.ingredients()
                    )
                ),
                id = NamespacedKey(choice.id.namespace, choice.id.key),
                recipe = choice.recipe()
            )
        }.toMutableList(),
        replace = replace,
    )
}