package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.Location
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.entity.CraftEntity

fun ClientPacketFactory.teleportEntity(
    entity: org.bukkit.entity.Entity,
    location: Location,
    onGround: Boolean,
    callback: (packet: Shard_Packet<ClientboundTeleportEntityPacketData>) -> Unit
): Shard_Packet<ClientboundTeleportEntityPacketData> {

    val data = ClientboundTeleportEntityPacketData(
        (entity as CraftEntity).handle,
        net.crystopia.crystalshard.dhl.shared.data.custom.Location(
            (location.world as CraftWorld).handle, location.z, location.y, location.z, location.yaw, location.pitch
        ), onGround
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }


    val shardPacket = Shard_Packet<ClientboundTeleportEntityPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}