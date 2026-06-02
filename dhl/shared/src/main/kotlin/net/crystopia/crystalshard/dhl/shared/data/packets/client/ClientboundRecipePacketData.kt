package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeState

data class ClientboundRecipePacketData(
    var state: RecipeState,
    var recipeIdsToChange: MutableList<NamespacedKey>,
    var recipeIdsToInit: MutableList<NamespacedKey>,
    var recipeBookSettings: net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeBookSettings
)
