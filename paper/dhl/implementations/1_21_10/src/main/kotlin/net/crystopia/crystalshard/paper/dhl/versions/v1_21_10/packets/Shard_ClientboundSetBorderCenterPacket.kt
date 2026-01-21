package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetBorderCenterPacket

class Shard_ClientboundSetBorderCenterPacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderCenterPacket {
        return ClientboundSetBorderCenterPacket(
            packetObj.border.build()
        )
    }
}