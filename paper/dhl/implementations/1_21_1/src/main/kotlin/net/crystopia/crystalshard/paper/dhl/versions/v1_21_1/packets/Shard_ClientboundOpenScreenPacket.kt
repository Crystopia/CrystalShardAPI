package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.gui.MenuType
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket

class Shard_ClientboundOpenScreenPacket : IPacket<ClientboundOpenScreenPacketData> {

    override fun createPacket(
        packetObj: ClientboundOpenScreenPacketData
    ): ClientboundOpenScreenPacket {
        val type = MenuType.convert(packetObj.type).type
        return ClientboundOpenScreenPacket(
            packetObj.id,
            type,
            PaperAdventure.asVanilla(packetObj.title)
        )
    }
}