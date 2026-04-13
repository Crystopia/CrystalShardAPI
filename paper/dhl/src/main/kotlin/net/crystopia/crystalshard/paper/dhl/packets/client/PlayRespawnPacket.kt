package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundRespawnPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.player.GameMode
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.Location
import org.bukkit.World

fun ClientPacketFactory.playRespawn(
    world: World,
    deathLocation: Location,
    gameMode: GameMode,
    isDebug: Boolean,
    isFlat: Boolean,
    portalCooldown: Int,
    datakept: Byte,
    callback: (packet: Shard_Packet<ClientboundRespawnPacketData>) -> Unit
): Shard_Packet<ClientboundRespawnPacketData> {

    val data = ClientboundRespawnPacketData(
        world, deathLocation, gameMode, isDebug, isFlat, portalCooldown, datakept
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.playRespawnPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.playRespawnPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.playRespawnPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.playRespawnPacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundRespawnPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}