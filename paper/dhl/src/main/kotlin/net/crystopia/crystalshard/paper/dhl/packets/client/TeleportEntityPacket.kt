package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.builder.LocationBuilder
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_1.data.packets.PAPER_1_21_1
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.data.packets.PAPER_1_21_10
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_11.data.packets.PAPER_1_21_11
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_9.data.packets.PAPER_1_21_9
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.Location
import org.bukkit.craftbukkit.entity.CraftEntity

fun ClientPacketFactory.teleportEntity(
    entity: org.bukkit.entity.Entity,
    location: Location,
    onGround: Boolean,
    callback: (packet: ClientPacket<ClientboundTeleportEntityPacketData>) -> Unit
): ClientPacket<ClientboundTeleportEntityPacketData> {
    val shardPacket = ClientPacket<ClientboundTeleportEntityPacketData>()

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_11(location), onGround
            )
            shardPacket.packetData = data
            ClientPacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_10(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.ClientPacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_9(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.ClientPacketBuilder.teleportEntityPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            val data = ClientboundTeleportEntityPacketData(
                (entity as CraftEntity).handle,
                LocationBuilder.PAPER_1_21_1(location), onGround
            )
            shardPacket.packetData = data
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.ClientPacketBuilder.teleportEntityPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}