package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket

class Shard_ClientboundOpenScreenPacket : IPacket<ClientboundOpenScreenPacketData> {

    override fun createPacket(
        packetObj: ClientboundOpenScreenPacketData
    ): ClientboundOpenScreenPacket {
        return ClientboundOpenScreenPacket(
            packetObj.id,
            packetObj.type,
            PaperAdventure.asVanilla(packetObj.title)
        )
    }
}