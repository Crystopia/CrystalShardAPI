package net.crystopia.crystalshard.dhl.shared.data.recipes

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry

open class RecipeEntry(
    open var id: NamespacedKey,
    open var flags: Byte,
    open var recipe: Any? = null,
    var recipeDisplay: Any? = null,
)
