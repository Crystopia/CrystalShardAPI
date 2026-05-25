package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.minecraft.network.protocol.game.ClientboundChunkBatchFinishedPacket

class Shard_ClientboundChunkBatchFinishedPacket :
    net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket<net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundChunkBatchFinishedPacketData> {

    override fun createPacket(
        packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundChunkBatchFinishedPacketData
    ): ClientboundChunkBatchFinishedPacket {
        return ClientboundChunkBatchFinishedPacket(
packetObj.size
        )
    }
}