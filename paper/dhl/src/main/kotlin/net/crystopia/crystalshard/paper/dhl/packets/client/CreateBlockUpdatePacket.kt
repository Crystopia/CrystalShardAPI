package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.Material
import org.bukkit.block.BlockType
import org.bukkit.craftbukkit.block.CraftBlockType

fun ClientPacketFactory.createBlockUpdate(
    pos: BlockPos,
    state: Material,
    callback: (packet: Shard_Packet<ClientboundBlockUpdatePacketData>) -> Unit
): Shard_Packet<ClientboundBlockUpdatePacketData> {

    val data = ClientboundBlockUpdatePacketData(
        pos, CraftBlockType.bukkitToMinecraft(state)
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.blockUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.blockUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.blockUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.blockUpdatePacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundBlockUpdatePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}