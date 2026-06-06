package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry

class Shard_ClientboundRecipeBookAddPacket : IClientPacket<ClientboundRecipeBookAddPacketData> {

override fun createPacket(
   packetObj: ClientboundRecipeBookAddPacketData
): ClientboundRecipeBookAddPacket {
    

    val data = packetObj.recipeEntries.map {
        ClientboundRecipeBookAddPacket.Entry(
            it.recipeDisplayEntry!! as RecipeDisplayEntry,
            it.flags
        )
    }.toList()

    return ClientboundRecipeBookAddPacket(
        data,
        packetObj.replace,
    )
}
}