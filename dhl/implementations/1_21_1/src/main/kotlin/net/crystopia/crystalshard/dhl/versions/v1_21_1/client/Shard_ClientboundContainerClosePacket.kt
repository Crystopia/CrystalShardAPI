package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundContainerClosePacket

class Shard_ClientboundContainerClosePacket : IClientPacket<ClientboundContainerClosePacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerClosePacketData
    ): ClientboundContainerClosePacket {
        return ClientboundContainerClosePacket(
            packetObj.id
        )
    }
}