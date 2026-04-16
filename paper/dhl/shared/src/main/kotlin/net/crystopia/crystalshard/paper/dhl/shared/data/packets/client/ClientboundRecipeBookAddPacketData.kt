package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.recipes.RecipeEntry

data class ClientboundRecipeBookAddPacketData(
    var recipeEntrys: MutableList<RecipeEntry>,
    var replace: Boolean,
)