package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTakeItemEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket

class Shard_ClientboundTakeItemEntityPacket : IPacket<ClientboundTakeItemEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTakeItemEntityPacketData
    ): ClientboundTakeItemEntityPacket {
        return ClientboundTakeItemEntityPacket(
            packetObj.itemId,
            packetObj.playerId,
            packetObj.amount,
        )
    }
}