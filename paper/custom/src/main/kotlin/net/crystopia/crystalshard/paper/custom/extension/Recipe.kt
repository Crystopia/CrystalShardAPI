package net.crystopia.crystalshard.paper.custom.extension

import org.bukkit.Bukkit
import org.bukkit.inventory.Recipe

fun Recipe.register(resendRecipe: Boolean) {
    Bukkit.addRecipe(this, resendRecipe)
}

