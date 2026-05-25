package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundChunkBatchStartPacket

class Shard_ClientboundChunkBatchStartPacket : IPacket<Int> {

    override fun createPacket(
        packetObj: Int
    ): ClientboundChunkBatchStartPacket {
        return ClientboundChunkBatchStartPacket.INSTANCE
    }
}