package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundRecipePacket
import net.minecraft.resources.ResourceLocation
import net.minecraft.stats.RecipeBookSettings
import net.minecraft.world.inventory.RecipeBookType

class Shard_ClientboundRecipePacket : IClientPacket<ClientboundRecipePacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipePacketData
    ): ClientboundRecipePacket {

        val settings = RecipeBookSettings()
        settings.setOpen(RecipeBookType.CRAFTING, packetObj.recipeBookSettings.craftingRecipeBookOpen)
        settings.setFiltering(RecipeBookType.CRAFTING, packetObj.recipeBookSettings.craftingRecipeBookFilterActive)

        settings.setOpen(RecipeBookType.BLAST_FURNACE, packetObj.recipeBookSettings.blastFurnaceRecipeBookOpen)
        settings.setFiltering(
            RecipeBookType.BLAST_FURNACE,
            packetObj.recipeBookSettings.blastFurnaceRecipeBookFilterActive
        )

        settings.setOpen(RecipeBookType.SMOKER, packetObj.recipeBookSettings.smokerRecipeBookOpen)
        settings.setFiltering(RecipeBookType.SMOKER, packetObj.recipeBookSettings.smokerRecipeBookFilterActive)

        settings.setOpen(RecipeBookType.FURNACE, packetObj.recipeBookSettings.smeltingRecipeBookOpen)
        settings.setFiltering(RecipeBookType.FURNACE, packetObj.recipeBookSettings.smeltingRecipeBookFilterActive)

        return ClientboundRecipePacket(
            ClientboundRecipePacket.State.valueOf(packetObj.state.name),
            packetObj.recipeIdsToChange.map { ResourceLocation.tryBuild(it.namespace, it.key) },
            packetObj.recipeIdsToInit.map { ResourceLocation.tryBuild(it.namespace, it.key) },
            settings
        )
    }
}