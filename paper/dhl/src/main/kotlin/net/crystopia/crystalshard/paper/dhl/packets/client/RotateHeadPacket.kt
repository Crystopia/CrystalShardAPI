package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.rotateHead(
    entity: Entity, yaw: Float, callback: (packet: ClientPacket<ClientboundRotateHeadPacketData>) -> Unit
): ClientPacket<ClientboundRotateHeadPacketData> {

    val data = ClientboundRotateHeadPacketData(
        (entity as CraftEntity).handle, yaw
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.rotateHeadPacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.rotateHeadPacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.rotateHeadPacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.rotateHeadPacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundRotateHeadPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}