package net.crystopia.crystalshard.builder

import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Villager
import org.bukkit.inventory.*
import org.bukkit.inventory.meta.trim.TrimPattern
import java.util.*


object RecipeBuilder {


    fun getRecipes(itemStack: ItemStack): Collection<Recipe> {
        return Bukkit.getServer().getRecipesFor(itemStack)
    }


    fun createShapedRecipe(
        result: ItemStack,
        mutableMap: MutableMap<Char, Material>,
        shape: String,
        addToPlayers: Boolean = false,
    ): ShapedRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())

        val recipe = ShapedRecipe(key, result)
        recipe.shape(shape)
        mutableMap.forEach { (key, value) ->
            recipe.setIngredient(key, value)
        }

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createFurnaceRecipe(
        result: ItemStack,
        material: Material,
        experience: Float,
        cookTime: Int,
        addToPlayers: Boolean = false,
    ): FurnaceRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = FurnaceRecipe(key, result, material, experience, cookTime)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createSmithingTrimRecipe(
        template: RecipeChoice = RecipeChoice.empty(),
        base: RecipeChoice = RecipeChoice.empty(),
        pattern: TrimPattern,
        addition: RecipeChoice = RecipeChoice.empty(),
        addToPlayers: Boolean = false,
    ): SmithingTrimRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = SmithingTrimRecipe(key, template, base, addition, pattern)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createSmithingTransformRecipe(
        result: ItemStack,
        template: RecipeChoice = RecipeChoice.empty(),
        base: RecipeChoice = RecipeChoice.empty(),
        addition: RecipeChoice = RecipeChoice.empty(),
        addToPlayers: Boolean = false,
    ): SmithingTransformRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = SmithingTransformRecipe(key, result, template, base, addition)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createBlastRecipe(
        result: ItemStack,
        material: Material,
        experience: Float,
        cookTime: Int,
        addToPlayers: Boolean = false,
    ): BlastingRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = BlastingRecipe(key, result, material, experience, cookTime)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createSmokingRecipe(
        result: ItemStack,
        material: Material,
        experience: Float,
        cookTime: Int,
        addToPlayers: Boolean = false,
    ): SmokingRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = SmokingRecipe(key, result, material, experience, cookTime)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createStonecuttingRecipe(
        result: ItemStack,
        material: Material,
        addToPlayers: Boolean = false,
    ): StonecuttingRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = StonecuttingRecipe(key, result, material)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
        return recipe
    }

    fun createCampfireRecipe(
        result: ItemStack,
        material: Material,
        experience: Float,
        cookTime: Int,
        addToPlayers: Boolean = false,
    ): CampfireRecipe {
        val key = NamespacedKey("uuid", UUID.randomUUID().toString())
        val recipe = CampfireRecipe(key, result, material, experience, cookTime)

        Bukkit.getServer().addRecipe(recipe, addToPlayers)
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
        return recipe
    }

    fun createVillagerRecipe(
        world: World,
        location: Location,
        recipeList: MutableMap<Int, MerchantRecipe>
    ): Villager {
        val villager = world.spawnEntity(
            location, EntityType.VILLAGER
        ) as Villager
        recipeList.forEach { (id, recipe) ->
            villager.setRecipe(id, recipe)
        }
        return villager
    }


}