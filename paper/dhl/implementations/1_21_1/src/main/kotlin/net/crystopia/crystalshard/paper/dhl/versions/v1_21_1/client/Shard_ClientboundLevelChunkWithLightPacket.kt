package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundLevelChunkWithLightPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.chunk.build
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket
import net.minecraft.world.level.lighting.LevelLightEngine
import org.bukkit.craftbukkit.CraftWorld

class Shard_ClientboundLevelChunkWithLightPacket : IPacket<ClientboundLevelChunkWithLightPacketData> {

    override fun createPacket(
        packetObj: ClientboundLevelChunkWithLightPacketData
    ): ClientboundLevelChunkWithLightPacket {
        return ClientboundLevelChunkWithLightPacket(
            packetObj.lightChunk.build(),
            LevelLightEngine(
                (packetObj.lightChunk.world as CraftWorld).handle.getChunkSource(),
                packetObj.isSkyLight,
                packetObj.isBlockLight
            ),
            packetObj.skyLight,
            packetObj.blockLight,
            false
        )
    }
}