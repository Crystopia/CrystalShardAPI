package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundChunkBatchFinishedPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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