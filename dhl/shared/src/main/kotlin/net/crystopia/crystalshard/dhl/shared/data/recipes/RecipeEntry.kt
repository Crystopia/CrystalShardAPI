package net.crystopia.crystalshard.dhl.shared.data.recipes

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

open class RecipeEntry(
    open var id: NamespacedKey,
    open var flags: Byte,
    open var recipe: Any? = null,
    var recipeDisplayEntry: Any? = null,
)
