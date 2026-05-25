package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry

data class ClientboundRecipeBookAddPacketData(
    var recipeEntrys: MutableList<RecipeEntry>,
    var replace: Boolean,
)