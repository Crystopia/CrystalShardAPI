package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket
import net.minecraft.world.item.crafting.RecipeBookCategory
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay
import net.minecraft.world.item.crafting.display.SlotDisplay
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.craftbukkit.inventory.CraftRecipe
import org.bukkit.craftbukkit.inventory.CraftShapedRecipe
import org.bukkit.craftbukkit.inventory.CraftShapelessRecipe
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.RecipeChoice
import java.util.Optional
import java.util.OptionalInt

class Shard_ClientboundRecipeBookAddPacket : IPacket<ClientboundRecipeBookAddPacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipeBookAddPacketData
    ): ClientboundRecipeBookAddPacket {

        // RecipeDisplayId id, RecipeDisplay display, OptionalInt group, RecipeBookCategory category, Optional<List<Ingredient>> craftingRequirements

        // ClientboundRecipeBookAddPacket
        // ClientboundUpdateRecipesPacket
        // ClientboundRecipeBookRemovePacket
        // ClientboundRecipeBookSettingsPacket
        // ClientboundPlaceGhostRecipePacket


        return ClientboundRecipeBookAddPacket(
            packetObj.recipeEntrys.map {
                when (it.recipe) {
                    is CraftShapedRecipe -> {
                        val recipe = it.recipe as CraftShapedRecipe
                        val ingredients = recipe.choiceMap.map { choiceEntry ->
                            return@map when (choiceEntry.value) {
                                is RecipeChoice.MaterialChoice -> {
                                    val choice = choiceEntry.value as RecipeChoice.MaterialChoice
                                    val slotDisplays = choice.choices.map { c ->
                                        val nmsStack = CraftItemStack.asNMSCopy(ItemStack(c))
                                        SlotDisplay.ItemStackSlotDisplay(
                                            nmsStack
                                        )
                                    }

                                    SlotDisplay.Composite(
                                        slotDisplays
                                    )
                                }

                                is RecipeChoice.ExactChoice -> {
                                    val choice = choiceEntry.value as RecipeChoice.ExactChoice
                                    val slotDisplays = choice.choices.map { c ->
                                        val nmsStack = CraftItemStack.asNMSCopy(c)
                                        SlotDisplay.ItemStackSlotDisplay(
                                            nmsStack
                                        )
                                    }

                                    SlotDisplay.Composite(
                                        slotDisplays
                                    )
                                }

                                else -> throw NotImplementedError()
                            }

                        }.toList()

                        val shapeDisplay = ShapedCraftingRecipeDisplay(
                            5, 5,
                            ingredients,
                            SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                            SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                        )

                        ClientboundRecipeBookAddPacket.Entry(
                            RecipeDisplayEntry(
                                RecipeDisplayId(it.id),
                                shapeDisplay,
                                OptionalInt.of(1), // TODO it.recipe.group,
                                RecipeBookCategory(), // it.recipe.category,
                                Optional.of(recipe.choiceMap.map { choice ->
                                    CraftRecipe.toIngredient(choice.value, false)
                                }.toList())
                            ),
                            it.notification,
                            it.highlight
                        )
                    }

                    is CraftShapelessRecipe -> {

                        val recipe = it.recipe as CraftShapelessRecipe
                        val ingredients = recipe.choiceList.map { choiceEntry ->
                            return@map when (choiceEntry) {
                                is RecipeChoice.MaterialChoice -> {
                                    val slotDisplays = choiceEntry.choices.map { c ->
                                        val nmsStack = CraftItemStack.asNMSCopy(ItemStack(c))
                                        SlotDisplay.ItemStackSlotDisplay(
                                            nmsStack
                                        )
                                    }

                                    SlotDisplay.Composite(
                                        slotDisplays
                                    )
                                }

                                is RecipeChoice.ExactChoice -> {
                                    val slotDisplays = choiceEntry.choices.map { c ->
                                        val nmsStack = CraftItemStack.asNMSCopy(c)
                                        SlotDisplay.ItemStackSlotDisplay(
                                            nmsStack
                                        )
                                    }

                                    SlotDisplay.Composite(
                                        slotDisplays
                                    )
                                }

                                else -> throw NotImplementedError()
                            }

                        }.toList()
                        val shapeDisplay = ShapelessCraftingRecipeDisplay(
                            ingredients,
                            SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                            SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                        )


                        ClientboundRecipeBookAddPacket.Entry(
                            RecipeDisplayEntry(
                                RecipeDisplayId(it.id),
                                shapeDisplay,
                                OptionalInt.of(1), // TODO it.recipe.group,
                                RecipeBookCategory(), // it.recipe.category,
                                Optional.of(recipe.choiceList.map { choice ->
                                    CraftRecipe.toIngredient(choice, false)
                                }.toList())
                            ),
                            it.notification,
                            it.highlight
                        )
                    }

                    else -> throw NotImplementedError()
                }
            },
            packetObj.replace,
        )
    }
}