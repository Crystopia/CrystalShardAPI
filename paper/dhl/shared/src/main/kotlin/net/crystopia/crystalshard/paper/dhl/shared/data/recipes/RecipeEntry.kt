package net.crystopia.crystalshard.paper.dhl.shared.data.recipes

import org.bukkit.inventory.Recipe

data class RecipeEntry(
    var id : Int,
    var recipe: Recipe,
    var highlight: Boolean,
    var notification: Boolean,
)
