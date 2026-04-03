package net.crystopia.crystalshard.paper.custom.smart

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.World
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager
import org.bukkit.inventory.*
import org.bukkit.inventory.meta.trim.TrimPattern

inline fun <reified T : Recipe> smartRecipe(
    recipe: T,
    callback: T.() -> Unit
): T {
    callback.invoke(recipe)
    return recipe
}

object RecipeCreator {

    fun shapedRecipe(
            key: NamespacedKey,
            result: ItemStack,
            mutableMap: MutableMap<Char, ItemStack>,
            vararg shape: String,
            callback: ShapedRecipe.() -> Unit
        ): ShapedRecipe {
            val recipe = ShapedRecipe(key, result)
            recipe.shape(*shape)
            mutableMap.forEach { (key, value) ->
                recipe.setIngredient(key, value)
            }

            callback.invoke(recipe)
            return recipe
        }

    fun furnaceRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            experience: Float,
            cookTime: Int,
            callback: FurnaceRecipe.() -> Unit
        ): FurnaceRecipe {
            val recipe = FurnaceRecipe(key, result, material, experience, cookTime)

            callback.invoke(recipe)
            return recipe
        }

    fun smithingTrimRecipe(
            key: NamespacedKey,
            template: RecipeChoice = RecipeChoice.empty(),
            base: RecipeChoice = RecipeChoice.empty(),
            pattern: TrimPattern,
            addition: RecipeChoice = RecipeChoice.empty(),
            callback: SmithingTrimRecipe.() -> Unit
        ): SmithingTrimRecipe {
            val recipe = SmithingTrimRecipe(key, template, base, addition, pattern)

            callback.invoke(recipe)
            return recipe
        }

    fun smithingTransformRecipe(
            key: NamespacedKey,
            result: ItemStack,
            template: RecipeChoice = RecipeChoice.empty(),
            base: RecipeChoice = RecipeChoice.empty(),
            addition: RecipeChoice = RecipeChoice.empty(),callback: SmithingTransformRecipe.() -> Unit
        ): SmithingTransformRecipe {
            val recipe = SmithingTransformRecipe(key, result, template, base, addition)

            callback.invoke(recipe)
            return recipe
        }

    fun blastRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            experience: Float,
            cookTime: Int,
            callback: BlastingRecipe.() -> Unit
        ): BlastingRecipe {
            val recipe = BlastingRecipe(key, result, material, experience, cookTime)

            callback.invoke(recipe)
            return recipe
        }

    fun smokingRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            experience: Float,
            cookTime: Int,
            callback: SmokingRecipe.() -> Unit
        ): SmokingRecipe {
            val recipe = SmokingRecipe(key, result, material, experience, cookTime)

            callback.invoke(recipe)
            return recipe
        }

    fun stonecuttingRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            callback: StonecuttingRecipe.() -> Unit
        ): StonecuttingRecipe {
            val recipe = StonecuttingRecipe(key, result, material)

            callback.invoke(recipe)
            return recipe
        }

    fun campfireRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            experience: Float,
            cookTime: Int,
            callback: CampfireRecipe.() -> Unit
        ): CampfireRecipe {
            val recipe = CampfireRecipe(key, result, material, experience, cookTime)

            callback.invoke(recipe)
            return recipe
        }

    fun merchantRecipe(
            result: ItemStack,
            uses: Int,
            maxUses: Int,
            experienceReward: Boolean,
            villagerExperience: Int,
            priceMultiplier: Float,
            demand: Int,
            specialPrice: Int,
            ignoreDiscounts: Boolean,
            ingredients: Pair<ItemStack, ItemStack?>,
            callback: MerchantRecipe.() -> Unit
        ): MerchantRecipe {
            val recipe = MerchantRecipe(
                result,
                uses,
                maxUses,
                experienceReward,
                villagerExperience,
                priceMultiplier,
                demand,
                specialPrice,
                ignoreDiscounts
            )

            recipe.adjust(result)
            recipe.addIngredient(
                ingredients.first
            )
            if (ingredients.second != null) {
                recipe.addIngredient(
                    ingredients.second!!
                )
            }
            callback.invoke(recipe)
            return recipe
        }

    fun villagerRecipe(
            world: World,
            location: Location,
            recipeList: MutableMap<Int, MerchantRecipe>,
            callback: Villager.() -> Unit
        ): Villager {
            val villager = world.spawnEntity(
                location, EntityType.VILLAGER
            ) as Villager
            recipeList.forEach { (id, recipe) ->
                villager.setRecipe(id, recipe)
            }
            callback.invoke(villager)
            return villager
        }
}