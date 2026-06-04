package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookRemovePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookRemovePacket
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry
import net.minecraft.world.item.crafting.display.RecipeDisplayId

class Shard_ClientboundRecipeBookRemovePacket : IClientPacket<ClientboundRecipeBookRemovePacketData> {

    override fun createPacket(
        packetObj: ClientboundRecipeBookRemovePacketData
    ): ClientboundRecipeBookRemovePacket {
        val data = packetObj.ids.map {
            RecipeDisplayId(
                it
            )
        }.toList()

        return ClientboundRecipeBookRemovePacket(
            data
        )
    }
}