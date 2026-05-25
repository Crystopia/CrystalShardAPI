package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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