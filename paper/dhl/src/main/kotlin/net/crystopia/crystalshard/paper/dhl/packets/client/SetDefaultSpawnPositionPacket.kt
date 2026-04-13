package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetDefaultSpawnPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.World

fun ClientPacketFactory.setDefaultSpawnPosition(
    world: World,
    pos: BlockPos,
    yaw: Float,
    pitch: Float,
    angle: Float?,
    callback: (packet: Shard_Packet<ClientboundSetDefaultSpawnPositionPacketData>) -> Unit
): Shard_Packet<ClientboundSetDefaultSpawnPositionPacketData> {

    val data = ClientboundSetDefaultSpawnPositionPacketData(
        world, pos, yaw, pitch, angle ?: 0F
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.setDefaultSpawnPosition(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.setDefaultSpawnPosition(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.setDefaultSpawnPosition(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.setDefaultSpawnPosition(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundSetDefaultSpawnPositionPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}