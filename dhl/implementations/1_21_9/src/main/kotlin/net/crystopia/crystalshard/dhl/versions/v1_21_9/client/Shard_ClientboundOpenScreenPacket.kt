package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.enums.gui.MenuType
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket

class Shard_ClientboundOpenScreenPacket : IClientPacket<ClientboundOpenScreenPacketData> {

    override fun createPacket(
        packetObj: ClientboundOpenScreenPacketData
    ): ClientboundOpenScreenPacket {
        val type = MenuType.convert(packetObj.type).type
        return ClientboundOpenScreenPacket(
            packetObj.id,
            type,
            packetObj.title
        )
    }
}