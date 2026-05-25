package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTakeItemEntityPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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