package net.crystopia.crystalshard.tests.paper.tests.new

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.extension.toDhlRecipeEntry
import net.crystopia.crystalshard.paper.dhl.packets.client.addRecipeBook
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.paper.util.smart.smartRecipe
import net.crystopia.crystalshard.tests.paper.tests.base.Test
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.recipe.CraftingBookCategory

object RecipeTest : Test("RecipeTest") {
    override fun command() {
        test {
            val player = Bukkit.getPlayer("_jespersen")!!

            val list = mutableListOf<NamespacedKey>()
            list.add(NamespacedKey("recipe", "egg2"))
            list.add(NamespacedKey("recipe", "egg"))
            player.discoveredRecipes.forEach { recipe ->
                println(recipe)
                list.add(recipe)
            }

            val recipe = mutableListOf(
                RecipeEntry(
                    id = NamespacedKey("recipe", "egg2"),
                    recipe = smartRecipe(
                        ShapedRecipe(
                            NamespacedKey("recipe", "egg2"),
                            ItemStack(Material.EGG),
                        )
                    ) {
                        category = CraftingBookCategory.EQUIPMENT
                        shape(
                            "AAA",
                            "ACA",
                            "AAA"
                        )
                        setIngredient(
                            'C',
                            Material.CHICKEN_SPAWN_EGG
                        )
                        setIngredient(
                            'A',
                            Material.APPLE
                        )
                    },
                    showNotification = 0x01,
                    highlight = 0x02,
                    group = "eier",
                ),
                RecipeEntry(
                    id = NamespacedKey("recipe", "egg"),
                    recipe = smartRecipe(
                        ShapedRecipe(
                            NamespacedKey("recipe", "egg"),
                            ItemStack(Material.DIAMOND),
                        )
                    ) {
                        category = CraftingBookCategory.EQUIPMENT
                        shape(
                            "AAA",
                            "ACA",
                            "AAA"
                        )
                        setIngredient(
                            'C',
                            Material.CHICKEN_SPAWN_EGG
                        )
                        setIngredient(
                            'A',
                            Material.APPLE
                        )
                    },
                    showNotification = 0x01,
                    highlight = 0x02,
                    group = "eier",
                )
            )

            player.discoveredRecipes.forEach { discoveredRecipe ->
                recipe.add(
                    Bukkit.getRecipe(discoveredRecipe)!!.toDhlRecipeEntry(
                        showNotification = true,
                        highlight = true,
                    )
                )
            }

            ClientPacketFactory.addRecipeBook(
                recipes = recipe,
                replace = true
            ) {
                it.send(mutableListOf(player))
            }

            /* 1.21.1 only
            ClientPacketFactory.recipePacket(
                state = RecipeState.ADD,
                recipeIdsToChange = list,
                recipeIdsToInit = list,
                recipeBookSettings = RecipeBookSettings(
                    craftingRecipeBookOpen = false,
                    craftingRecipeBookFilterActive = false,
                    smeltingRecipeBookOpen = false,
                    smeltingRecipeBookFilterActive = false,
                    blastFurnaceRecipeBookOpen = false,
                    blastFurnaceRecipeBookFilterActive = false,
                    smokerRecipeBookOpen = false,
                    smokerRecipeBookFilterActive = false
                )
            ) {
                it.send(mutableListOf(player))
            }
             */

            println("Successfully added ${player.name}")
        }
    }
}