package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.dhl.shared.enums.entities.LookAnchor
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.ClientPacketBuilder
import org.bukkit.craftbukkit.entity.CraftEntity

fun ClientPacketFactory.updatePlayerLookAt(
    entity: org.bukkit.entity.Entity,
    fromAnchor: LookAnchor,
    toAnchor: LookAnchor,
    x: Double,
    y: Double,
    z: Double,
    callback: (packet: ClientPacket<ClientboundPlayerLookAtPacketData>) -> Unit
): ClientPacket<ClientboundPlayerLookAtPacketData> {

    val data = ClientboundPlayerLookAtPacketData(
        (entity as CraftEntity).handle, fromAnchor, toAnchor, x, y, z
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.updatePlayerLookAt(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.updatePlayerLookAt(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundPlayerLookAtPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}