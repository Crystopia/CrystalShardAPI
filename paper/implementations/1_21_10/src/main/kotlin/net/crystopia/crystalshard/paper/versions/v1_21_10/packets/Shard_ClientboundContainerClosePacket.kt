package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundContainerClosePacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
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