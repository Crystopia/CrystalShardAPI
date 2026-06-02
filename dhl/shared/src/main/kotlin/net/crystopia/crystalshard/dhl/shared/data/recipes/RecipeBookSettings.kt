package net.crystopia.crystalshard.dhl.shared.data.recipes

data class RecipeBookSettings(
    var craftingRecipeBookOpen: Boolean,
    var craftingRecipeBookFilterActive: Boolean,
    var smeltingRecipeBookOpen: Boolean,
    var smeltingRecipeBookFilterActive: Boolean,
    var blastFurnaceRecipeBookOpen: Boolean,
    var blastFurnaceRecipeBookFilterActive: Boolean,
    var smokerRecipeBookOpen: Boolean,
    var smokerRecipeBookFilterActive: Boolean,
)
