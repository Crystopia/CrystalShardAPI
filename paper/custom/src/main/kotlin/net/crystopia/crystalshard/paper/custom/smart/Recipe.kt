package net.crystopia.crystalshard.paper.custom.smart

import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager
import org.bukkit.inventory.*
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.meta.trim.TrimPattern
import java.util.*

class Recipe {

    companion object {
        fun getRecipes(itemStack: ItemStack): Collection<Recipe> {
            return Bukkit.getServer().getRecipesFor(itemStack)
        }

        fun createShapedRecipe(
            key: NamespacedKey,
            result: ItemStack,
            mutableMap: MutableMap<Char, Material>,
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

        fun createFurnaceRecipe(
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

        fun createSmithingTrimRecipe(
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

        fun createSmithingTransformRecipe(
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

        fun createBlastRecipe(
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

        fun createSmokingRecipe(
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

        fun createStonecuttingRecipe(
            key: NamespacedKey,
            result: ItemStack,
            material: Material,
            callback: StonecuttingRecipe.() -> Unit
        ): StonecuttingRecipe {
            val recipe = StonecuttingRecipe(key, result, material)

            callback.invoke(recipe)
            return recipe
        }

        fun createCampfireRecipe(
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

        fun createMerchantRecipe(
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

        fun createVillagerRecipe(
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
}