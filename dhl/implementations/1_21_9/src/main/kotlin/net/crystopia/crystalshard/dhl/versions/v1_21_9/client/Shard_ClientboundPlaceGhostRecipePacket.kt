package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlaceGhostRecipePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundPlaceGhostRecipePacket
import net.minecraft.world.item.crafting.display.RecipeDisplay

class Shard_ClientboundPlaceGhostRecipePacket : IClientPacket<ClientboundPlaceGhostRecipePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlaceGhostRecipePacketData
    ): ClientboundPlaceGhostRecipePacket {
        return ClientboundPlaceGhostRecipePacket(
            packetObj.containerId, packetObj.recipeDisplay as RecipeDisplay,
        )
    }
}