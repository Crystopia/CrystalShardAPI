package net.crystopia.crystalshard.dhl.shared.data.packets.client

data class ClientboundPlaceGhostRecipePacketData(
    var containerId: Int,
    /**
     * RecipeDisplay
     */
    var recipeDisplay: Any,
    /**
     * RecipeHolder<*>
     */
    var recipe: Any
)
