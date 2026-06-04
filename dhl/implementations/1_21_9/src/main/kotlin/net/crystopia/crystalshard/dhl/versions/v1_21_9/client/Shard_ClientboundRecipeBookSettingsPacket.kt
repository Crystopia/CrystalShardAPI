package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookSettingsPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookSettingsPacket
import net.minecraft.stats.RecipeBookSettings
import net.minecraft.world.inventory.RecipeBookType

class Shard_ClientboundRecipeBookSettingsPacket : IClientPacket<ClientboundRecipeBookSettingsPacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipeBookSettingsPacketData
    ): ClientboundRecipeBookSettingsPacket {
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


        return ClientboundRecipeBookSettingsPacket(
            settings
        )
    }
}