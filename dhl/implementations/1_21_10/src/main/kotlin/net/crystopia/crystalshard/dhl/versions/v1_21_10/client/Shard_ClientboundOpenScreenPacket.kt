package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenScreenPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundOpenScreenPacket

class Shard_ClientboundOpenScreenPacket : IPacket<ClientboundOpenScreenPacketData> {

    override fun createPacket(
        packetObj: ClientboundOpenScreenPacketData
    ): ClientboundOpenScreenPacket {
        return ClientboundOpenScreenPacket(
            packetObj.id,
            net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.gui.MenuType.convert(packetObj.type),
            packetObj.title
        )
    }
}