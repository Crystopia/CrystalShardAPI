package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry
import net.minecraft.world.item.crafting.Recipe

data class ClientboundRecipeBookAddPacketData(
    var recipeDisplayEntries: List<RecipeEntry>,
    var replace: Boolean,
)