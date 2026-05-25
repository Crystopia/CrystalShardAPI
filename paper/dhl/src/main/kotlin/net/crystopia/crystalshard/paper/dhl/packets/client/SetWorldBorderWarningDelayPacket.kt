package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.dhl.shared.data.world.WorldBorder
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.craftbukkit.CraftWorld

fun ClientPacketFactory.setWorldBorderWarningDelay(
    border: net.crystopia.crystalshard.paper.dhl.types.world.WorldBorder,
    callback: (packet: Shard_Packet<ClientboundBorderPacketData>) -> Unit
): Shard_Packet<ClientboundBorderPacketData> {

    val data = ClientboundBorderPacketData(
        WorldBorder(
            world = (border.world as CraftWorld).handle,
            size = border.size,
            centerX = border.centerX,
            centerZ = border.centerZ,
            absoluteMaxSize = border.absoluteMaxSize,
            damagePerBlock = border.damagePerBlock,
            safeZone = border.safeZone,
            warningBlocks = border.warningBlocks,
            warningTime = border.warningTime,
            oldLerpSize = border.oldLerpSize,
            newLerpSize = border.newLerpSize,
            lerpTime = border.lerpTime,
            lerpTimeStart = border.lerpTimeStart,
        )
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.setWorldBorderWarningDelay(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.setWorldBorderWarningDelay(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.setWorldBorderWarningDelay(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.setWorldBorderWarningDelay(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundBorderPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}