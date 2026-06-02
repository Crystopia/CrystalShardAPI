package net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.types

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeEntry

data class RecipeEntry(
    override var id: NamespacedKey,
    override var flags: Byte,
    override var recipe: Any? = null,
) : RecipeEntry(id, flags, recipe)
