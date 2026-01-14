package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket

class Shard_ClientboundContainerClosePacket : IPacket<ClientboundContainerClosePacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerClosePacketData
    ): ClientboundContainerClosePacket {
        return ClientboundContainerClosePacket(
            packetObj.id
        )
    }
}