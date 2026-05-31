package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRecipeBookAddPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRecipeBookAddPacket

class Shard_ClientboundRecipeBookAddPacket : IPacket<ClientboundRecipeBookAddPacketData> {

override fun createPacket(
   packetObj: ClientboundRecipeBookAddPacketData
): ClientboundRecipeBookAddPacket {
    val data = packetObj.recipeDisplayEntries.map {
        ClientboundRecipeBookAddPacket.Entry(
            it.recipeDisplay,
            it.flags
        )
    }.toList()

    return ClientboundRecipeBookAddPacket(
        data,
        packetObj.replace,
    )
}
}