package net.crystopia.crystalshard.dhl.shared.data.recipes

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

open class RecipeEntry(
    open var id: NamespacedKey,
    open var flags: Byte,
    /**
     * 1.21.x net.minecraft.world.item.crafting.Recipe<*>;
     */
    open var recipe: Any? = null,
    /**
     * 1.21.9-1.21.11 RecipeDisplay;
     */
    var recipeDisplayEntry: Any? = null,
)
