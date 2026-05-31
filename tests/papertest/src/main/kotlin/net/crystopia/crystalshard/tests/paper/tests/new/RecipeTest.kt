package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.paper.custom.smart.smartRecipe
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.addRecipeBook
import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.crystopia.crystalshard.tests.paper.tests.base.ITest
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

class RecipeTest(name: String, sender: CommandSender, args: CommandArguments) : ITest(name, sender, args) {
    override fun command() {
        test {
            val player = sender as Player

            ClientPacketFactory.addRecipeBook(
                recipes = mutableListOf(
                    RecipeEntry(
                        id = NamespacedKey("recipe", "egg"),
                        order = 1,
                        recipe = smartRecipe(
                            ShapedRecipe(
                                NamespacedKey("recipe", "egg"),
                                ItemStack(Material.EGG),
                            )
                        ) {
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
                        showNotification = 0x00,
                        highlight = 0x00,
                        group = 2,
                        category = RecipeBookCategories.CRAFTING_MISC,
                        ingredients = mutableSetOf()
                    ),
                    RecipeEntry(
                        id = NamespacedKey("recipe", "egg"),
                        order = 2,
                        recipe = smartRecipe(
                            ShapedRecipe(
                                NamespacedKey("recipe", "egg"),
                                ItemStack(Material.DIAMOND),
                            )
                        ) {
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
                        showNotification = 0x00,
                        highlight = 0x00,
                        group = 1,
                        category = RecipeBookCategories.CRAFTING_MISC,
                        ingredients = mutableSetOf()
                    )
                ),
                replace = true
            ) {
                it.send(mutableListOf(player))
            }

            println("Successfully added ${player.name}")
        }
    }
}