package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundChunkBatchFinishedPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundChunkBatchStartPacket
import net.minecraft.network.protocol.game.ClientboundChunksBiomesPacket
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket

class Shard_ClientboundChunkBatchStartPacket : IPacket<Int> {

    override fun createPacket(
        packetObj: Int
    ): ClientboundChunkBatchStartPacket {
        return ClientboundChunkBatchStartPacket.INSTANCE
    }
}