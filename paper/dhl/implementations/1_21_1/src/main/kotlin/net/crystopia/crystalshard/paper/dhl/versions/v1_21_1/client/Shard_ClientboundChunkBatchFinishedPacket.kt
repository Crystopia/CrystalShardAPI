package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundChunkBatchFinishedPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundChunkBatchFinishedPacket

class Shard_ClientboundChunkBatchFinishedPacket : IPacket<ClientboundChunkBatchFinishedPacketData> {

    override fun createPacket(
        packetObj: ClientboundChunkBatchFinishedPacketData
    ): ClientboundChunkBatchFinishedPacket {
        return ClientboundChunkBatchFinishedPacket(
packetObj.size
        )
    }
}