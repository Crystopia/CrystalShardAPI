package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundUpdateRecipesPacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeHolder

class Shard_ClientboundUpdateRecipesPacket : IPacket<ClientboundRecipeBookAddPacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipeBookAddPacketData
    ): ClientboundUpdateRecipesPacket {
        val recipes = packetObj.recipeDisplayEntries.map {
            RecipeHolder(
                ResourceLocation.tryBuild(it.id.namespace, it.id.key)!!,
                it.recipe
            )
        }
        return ClientboundUpdateRecipesPacket(
            recipes
        )
    }
}