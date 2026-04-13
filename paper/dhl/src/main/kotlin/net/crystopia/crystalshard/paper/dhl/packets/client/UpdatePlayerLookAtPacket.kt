package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.paper.dhl.ClientPacketFactory
import net.crystopia.crystalshard.paper.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.LookAnchor
import net.crystopia.crystalshard.paper.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.shared.utils.ServerUtil
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.general.PacketBuilder
import org.bukkit.World

fun ClientPacketFactory.updatePlayerLookAt(
    entityId: Int,
    world: World,
    fromAnchor: LookAnchor,
    toAnchor: LookAnchor,
    x: Double,
    y: Double,
    z: Double,
    callback: (packet: Shard_Packet<ClientboundPlayerLookAtPacketData>) -> Unit
): Shard_Packet<ClientboundPlayerLookAtPacketData> {

    val data = ClientboundPlayerLookAtPacketData(
        entityId, world, fromAnchor, toAnchor, x, y, z
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.general.PacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.general.PacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.general.PacketBuilder.updatePlayerLookAt(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundPlayerLookAtPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}