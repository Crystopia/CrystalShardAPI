package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetBorderSizePacket

class Shard_ClientboundSetBorderSizePacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderSizePacket {
        return ClientboundSetBorderSizePacket(
            packetObj.border.build()
        )
    }
}