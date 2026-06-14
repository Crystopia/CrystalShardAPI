package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey

data class ClientboundPlaceGhostRecipePacketData(
    var id: NamespacedKey,
    var containerId: Int,
    /**
     * 1.21.9-1.21.11 - RecipeDisplay;
     */
    var recipeDisplayEntry: Any?,
    /**
     * 1.21.x RecipeHolder<*>;
     */
    var recipe: Any?
)
