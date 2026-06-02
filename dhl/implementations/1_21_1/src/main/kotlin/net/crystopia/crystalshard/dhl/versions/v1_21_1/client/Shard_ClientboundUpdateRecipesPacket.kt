package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRecipePacket
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.item.crafting.ShapedRecipe

class Shard_ClientboundUpdateRecipesPacket : IPacket<ClientboundRecipeBookAddPacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipeBookAddPacketData
    ): ClientboundUpdateRecipesPacket {
        val recipes = packetObj.recipeEntries.map {
            println(it.recipe)
            RecipeHolder(
                ResourceLocation.tryBuild(it.id.namespace, it.id.key)!!,
                it.recipe!! as Recipe<*>
            )
        }

        return ClientboundUpdateRecipesPacket(
            recipes
        )
    }
}