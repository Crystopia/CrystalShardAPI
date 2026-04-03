package net.crystopia.crystalshard.paper.core.extension

import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

fun ItemStack.getRecipesFor(): List<Recipe> {
    return Bukkit.getRecipesFor(this)
}