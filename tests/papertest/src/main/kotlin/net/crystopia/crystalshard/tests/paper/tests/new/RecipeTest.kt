package net.crystopia.crystalshard.tests.paper.tests.new

import dev.jorel.commandapi.executors.CommandArguments
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.data.recipes.RecipeBookSettings
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeBookCategories
import net.crystopia.crystalshard.dhl.shared.enums.recipes.RecipeState
import net.crystopia.crystalshard.paper.custom.smart.smartRecipe
import net.crystopia.crystalshard.paper.dhl.extension.send
import net.crystopia.crystalshard.paper.dhl.packets.client.addRecipeBook
import net.crystopia.crystalshard.paper.dhl.packets.client.recipePacket
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
                        id = NamespacedKey("recipe", "egg2"),
                        order = 1,
                        recipe = smartRecipe(
                            ShapedRecipe(
                                NamespacedKey("recipe", "egg2"),
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
                        category = RecipeBookCategories.MISC,
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
                        category = RecipeBookCategories.MISC,
                        ingredients = mutableSetOf()
                    )
                ),
                replace = true
            ) {
                it.send(mutableListOf(player))
            }

            ClientPacketFactory.recipePacket(
                state = RecipeState.ADD,
                recipeIdsToChange = mutableListOf(NamespacedKey("recipe", "egg")),
                recipeIdsToInit = mutableListOf(NamespacedKey("recipe", "egg")),
                recipeBookSettings = RecipeBookSettings(
                    craftingRecipeBookOpen = true,
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

            println("Successfully added ${player.name}")
        }
    }
}