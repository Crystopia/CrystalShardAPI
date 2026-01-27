package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket

class Shard_ClientboundSetBorderLerpSizePacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderLerpSizePacket {
        return ClientboundSetBorderLerpSizePacket(
            packetObj.border.build()
        )
    }
}