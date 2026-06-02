package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAnimatePacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.createAnimation(
    entity: Entity, animationId: Int, callback: (packet: ClientPacket<ClientboundAnimatePacketData>) -> Unit
): ClientPacket<ClientboundAnimatePacketData> {

    val data = ClientboundAnimatePacketData(
        (entity as CraftEntity).handle, animationId
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.animatePacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.animatePacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.animatePacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.animatePacket(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundAnimatePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}