package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundLevelChunkWithLightPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.chunk.build
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.lighting.LevelLightEngine

class Shard_ClientboundLevelChunkWithLightPacket : IPacket<ClientboundLevelChunkWithLightPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelChunkWithLightPacketData
    ): ClientboundLevelChunkWithLightPacket {
        return ClientboundLevelChunkWithLightPacket(
            packetObj.lightChunk.build(),
            LevelLightEngine(
                packetObj.lightChunk.world.chunkSource,
                packetObj.isSkyLight,
                packetObj.isBlockLight
            ),
            packetObj.skyLight,
            packetObj.blockLight,
            false
        )
    }
}