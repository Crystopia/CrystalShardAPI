package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.builder.LocationBuilder
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.PAPER_1_21_1
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.PAPER_1_21_10
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets.PAPER_1_21_11
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.PAPER_1_21_9
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
    val shardPacket = Shard_Packet<ClientboundTeleportEntityPacketData>()

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_11(location), onGround
            )
            shardPacket.packetData = data
            PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_10(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_9(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_1(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.teleportEntityPacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}