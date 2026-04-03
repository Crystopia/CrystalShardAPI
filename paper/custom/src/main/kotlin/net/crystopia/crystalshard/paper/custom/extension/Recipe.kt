package net.crystopia.crystalshard.paper.custom.extension

import org.bukkit.Bukkit
import org.bukkit.inventory.Recipe

fun Recipe.register(resendRecipe: Boolean): Recipe {
    Bukkit.addRecipe(this, resendRecipe)
    return this
}

