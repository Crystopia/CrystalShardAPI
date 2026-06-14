package net.crystopia.crystalshard.paper.dhl.converter.v1_21_11


import net.crystopia.crystalshard.paper.dhl.types.recipes.RecipeEntry
import net.minecraft.core.Holder
import net.minecraft.world.item.crafting.ShapedRecipePattern
import net.minecraft.world.item.crafting.StonecutterRecipe
import net.minecraft.world.item.crafting.TransmuteResult
import net.minecraft.world.item.crafting.display.*
import net.minecraft.world.item.equipment.trim.TrimPattern
import org.bukkit.craftbukkit.inventory.*
import org.bukkit.craftbukkit.inventory.trim.CraftTrimPattern
import org.bukkit.inventory.*
import java.util.*

fun RecipeEntry.slotDisplays(): List<SlotDisplay> {
    val choices: List<RecipeChoice?> = when (val recipe = this.recipe) {
        is ShapedRecipe -> {
            val shape = recipe.shape
            val width = shape.maxOf { it.length }

            shape.flatMap { row ->
                row.padEnd(width).map { char ->
                    val choice = recipe.choiceMap[char]
                    choice
                }
            }.toMutableList()
        }

        is ShapelessRecipe -> recipe.choiceList
        is FurnaceRecipe -> listOf(recipe.inputChoice)
        is BlastingRecipe -> listOf(recipe.inputChoice)
        is SmokingRecipe -> listOf(recipe.inputChoice)
        is CampfireRecipe -> listOf(recipe.inputChoice)
        is StonecuttingRecipe -> listOf(recipe.inputChoice)
        is SmithingTransformRecipe -> listOf(recipe.template, recipe.base, recipe.addition)
        is SmithingTrimRecipe -> listOf(recipe.template, recipe.base, recipe.addition)
        is TransmuteRecipe -> listOf(recipe.input, recipe.material)
        else -> throw NotImplementedError("Unsupported recipe type: ${recipe::class.simpleName}")
    }

    return choices.map { choice ->
        when (choice) {
            is RecipeChoice.MaterialChoice -> {
                val slotDisplays = choice.choices.map { material ->
                    SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(ItemStack(material)))
                }
                SlotDisplay.Composite(slotDisplays)
            }

            is RecipeChoice.ExactChoice -> {
                val slotDisplays = choice.choices.map { item ->
                    SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(item))
                }
                SlotDisplay.Composite(slotDisplays)
            }

            null -> SlotDisplay.Empty.INSTANCE
            else -> throw NotImplementedError("Unsupported RecipeChoice type: ${choice::class.simpleName}")
        }
    }
}

fun RecipeEntry.display(): RecipeDisplay {
    return when (this.recipe) {
        is ShapedRecipe -> {
            val recipe = CraftShapedRecipe.fromBukkitRecipe(this.recipe as ShapedRecipe)
            val slotDisplays = this.slotDisplays()
            val width = recipe.shape.maxOf { it.length }
            val height = recipe.shape.size
            ShapedCraftingRecipeDisplay(
                width,
                height,
                slotDisplays,
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        is ShapelessRecipe -> {
            val recipe = CraftShapelessRecipe.fromBukkitRecipe(this.recipe as ShapelessRecipe)
            val slotDisplays = slotDisplays()
            ShapelessCraftingRecipeDisplay(
                slotDisplays,
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        is FurnaceRecipe -> {
            val recipe = CraftFurnaceRecipe.fromBukkitRecipe(this.recipe as FurnaceRecipe)
            val slotDisplays = slotDisplays()
            FurnaceRecipeDisplay(
                slotDisplays.firstOrNull() ?: SlotDisplay.Empty.INSTANCE,
                CraftRecipe.toIngredient(recipe.inputChoice, true).display(),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                recipe.cookingTime,
                recipe.experience
            )
        }

        is BlastingRecipe -> {
            val recipe = CraftBlastingRecipe.fromBukkitRecipe(this.recipe as BlastingRecipe)
            val slotDisplays = slotDisplays()
            FurnaceRecipeDisplay(
                slotDisplays.firstOrNull() ?: SlotDisplay.Empty.INSTANCE,
                CraftRecipe.toIngredient(recipe.inputChoice, true).display(),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                recipe.cookingTime,
                recipe.experience
            )
        }

        is SmokingRecipe -> {
            val recipe = CraftSmokingRecipe.fromBukkitRecipe(this.recipe as SmokingRecipe)
            val slotDisplays = slotDisplays()
            FurnaceRecipeDisplay(
                slotDisplays.firstOrNull() ?: SlotDisplay.Empty.INSTANCE,
                CraftRecipe.toIngredient(recipe.inputChoice, true).display(),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                recipe.cookingTime,
                recipe.experience
            )
        }

        is CampfireRecipe -> {
            val recipe = CraftCampfireRecipe.fromBukkitRecipe(this.recipe as CampfireRecipe)
            val slotDisplays = slotDisplays()
            FurnaceRecipeDisplay(
                slotDisplays.firstOrNull() ?: SlotDisplay.Empty.INSTANCE,
                CraftRecipe.toIngredient(recipe.inputChoice, true).display(),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                recipe.cookingTime,
                recipe.experience
            )
        }

        is StonecuttingRecipe -> {
            val recipe = CraftStonecuttingRecipe.fromBukkitRecipe(this.recipe as StonecuttingRecipe)
            val slotDisplays = slotDisplays()
            StonecutterRecipeDisplay(
                slotDisplays.firstOrNull() ?: SlotDisplay.Empty.INSTANCE,
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        is SmithingTransformRecipe -> {
            val recipe = CraftSmithingTransformRecipe.fromBukkitRecipe(this.recipe as SmithingTransformRecipe)
            val slotDisplays = slotDisplays().toMutableList()
            SmithingRecipeDisplay(
                slotDisplays.getOrElse(0) { SlotDisplay.Empty.INSTANCE }, // template
                slotDisplays.getOrElse(1) { SlotDisplay.Empty.INSTANCE }, // base
                slotDisplays.getOrElse(2) { SlotDisplay.Empty.INSTANCE }, // addition
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        is SmithingTrimRecipe -> {
            val recipe = CraftSmithingTrimRecipe.fromBukkitRecipe(this.recipe as SmithingTrimRecipe)
            val slotDisplays = slotDisplays().toMutableList()
            SmithingRecipeDisplay(
                slotDisplays.getOrElse(0) { SlotDisplay.Empty.INSTANCE }, // template
                slotDisplays.getOrElse(1) { SlotDisplay.Empty.INSTANCE }, // base
                slotDisplays.getOrElse(2) { SlotDisplay.Empty.INSTANCE }, // addition
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        is TransmuteRecipe -> {
            val recipe = this.recipe as TransmuteRecipe
            val slotDisplays = slotDisplays().toMutableList()
            SmithingRecipeDisplay(
                SlotDisplay.Empty.INSTANCE,                                           // no Template
                slotDisplays.getOrElse(0) { SlotDisplay.Empty.INSTANCE },            // input
                slotDisplays.getOrElse(1) { SlotDisplay.Empty.INSTANCE },            // material
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
                SlotDisplay.ItemStackSlotDisplay(CraftItemStack.asNMSCopy(recipe.result)),
            )
        }

        else -> throw NotImplementedError("Unsupported recipe type: ${this.recipe::class.simpleName}")
    }
}

fun RecipeEntry.recipe(): net.minecraft.world.item.crafting.Recipe<*> {
    return when (val recipe = this.recipe) {
        is ShapedRecipe -> {
            val bukkit = CraftShapedRecipe.fromBukkitRecipe(recipe)
            val shape = bukkit.shape
            val width = shape.maxOf { it.length }
            val ingredients = shape.flatMap { row ->
                row.padEnd(width).map { char ->
                    val choice = bukkit.choiceMap[char]
                    if (choice == null) Optional.empty()
                    else Optional.of(CraftRecipe.toIngredient(choice, true))
                }
            }
            net.minecraft.world.item.crafting.ShapedRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                ShapedRecipePattern(width, shape.size, ingredients, Optional.empty()),
                CraftItemStack.asNMSCopy(bukkit.result),
            )
        }

        is ShapelessRecipe -> {
            val bukkit = CraftShapelessRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.ShapelessRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.choiceList.map { CraftRecipe.toIngredient(it, true) })
        }

        is FurnaceRecipe -> {
            val bukkit = CraftFurnaceRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmeltingRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftRecipe.toIngredient(bukkit.inputChoice, true),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is BlastingRecipe -> {
            val bukkit = CraftBlastingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.BlastingRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftRecipe.toIngredient(bukkit.inputChoice, true),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is SmokingRecipe -> {
            val bukkit = CraftSmokingRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmokingRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftRecipe.toIngredient(bukkit.inputChoice, true),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is CampfireRecipe -> {
            val bukkit = CraftCampfireRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.CampfireCookingRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftRecipe.toIngredient(bukkit.inputChoice, true),
                CraftItemStack.asNMSCopy(bukkit.result),
                bukkit.experience,
                bukkit.cookingTime,
            )
        }

        is StonecuttingRecipe -> {
            val bukkit = CraftStonecuttingRecipe.fromBukkitRecipe(recipe)
            StonecutterRecipe(
                bukkit.group,
                CraftRecipe.toIngredient(bukkit.inputChoice, true),
                CraftItemStack.asNMSCopy(bukkit.result),
            )
        }

        is SmithingTransformRecipe -> {
            val bukkit = CraftSmithingTransformRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmithingTransformRecipe(
                Optional.of(CraftRecipe.toIngredient(bukkit.template, true)),
                CraftRecipe.toIngredient(bukkit.base, true),
                Optional.of(CraftRecipe.toIngredient(bukkit.addition, true)),
                TransmuteResult(
                    CraftItemStack.asNMSCopy(bukkit.result).item
                )
            )
        }

        is SmithingTrimRecipe -> {
            val bukkit = CraftSmithingTrimRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.SmithingTrimRecipe(
                CraftRecipe.toIngredient(bukkit.template, true),
                CraftRecipe.toIngredient(bukkit.base, true),
                CraftRecipe.toIngredient(bukkit.addition, true),
                Holder.direct(
                    TrimPattern(
                        CraftTrimPattern.bukkitToMinecraftHolder(bukkit.trimPattern).value().assetId,
                        CraftTrimPattern.bukkitToMinecraftHolder(bukkit.trimPattern).value().description,
                        CraftTrimPattern.bukkitToMinecraftHolder(bukkit.trimPattern).value().decal
                    )
                )
            )
        }

        is TransmuteRecipe -> {
            val bukkit = CraftTransmuteRecipe.fromBukkitRecipe(recipe)
            net.minecraft.world.item.crafting.TransmuteRecipe(
                bukkit.group,
                CraftRecipe.getCategory(bukkit.category),
                CraftRecipe.toIngredient(bukkit.input, true),
                CraftRecipe.toIngredient(bukkit.material, true),
                TransmuteResult(
                    CraftItemStack.asNMSCopy(bukkit.result).item
                ),
            )
        }

        else -> throw IllegalArgumentException("Unbekannter Recipe-Typ: ${recipe::class.simpleName}")
    }
}