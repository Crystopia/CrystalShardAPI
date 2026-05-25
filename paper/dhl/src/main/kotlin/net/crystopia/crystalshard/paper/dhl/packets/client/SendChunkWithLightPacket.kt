package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.chunk.LevelChunk
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundLevelChunkWithLightPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.craftbukkit.CraftWorld
import java.util.*

fun ClientPacketFactory.sendChunkWithLight(
    lightChunk: net.crystopia.crystalshard.paper.dhl.types.chunk.LevelChunk,
    skyLight: BitSet,
    blockLight: BitSet,
    isSkyLight: Boolean,
    isBlockLight: Boolean,
    callback: (packet: Shard_Packet<ClientboundLevelChunkWithLightPacketData>) -> Unit
): Shard_Packet<ClientboundLevelChunkWithLightPacketData> {

    val data = ClientboundLevelChunkWithLightPacketData(
        LevelChunk(
            world = (lightChunk.world as CraftWorld).handle,
            x = lightChunk.x,
            y = lightChunk.y
        ), skyLight, blockLight, isSkyLight, isBlockLight
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.sendChunkWithLight(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.sendChunkWithLight(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.sendChunkWithLight(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.sendChunkWithLight(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundLevelChunkWithLightPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}