package net.crystopia.crystalshard.dhl.shared.data.recipes

import net.minecraft.world.item.crafting.Recipe

data class RecipeEntry(
    var id : Int,
    var recipe: Recipe<*>,
    var highlight: Boolean,
    var notification: Boolean,
)
