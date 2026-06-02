package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder
import org.bukkit.craftbukkit.entity.CraftEntity
import org.bukkit.entity.Entity

fun ClientPacketFactory.moveEntity(
    mode: EntityMoveMode,
    entity: Entity,
    xa: Short,
    ya: Short,
    za: Short,
    yRot: Byte,
    xRot: Byte,
    onGround: Boolean,
    hasRot: Boolean,
    hasPos: Boolean,
    callback: (packet: ClientPacket<ClientboundMoveEntityPacketData>) -> Unit
): ClientPacket<ClientboundMoveEntityPacketData> {

    val data = ClientboundMoveEntityPacketData(
        mode, (entity as CraftEntity).handle, xa, ya, za, yRot, xRot, onGround, hasRot, hasPos
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            ClientPacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.moveEntity(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundMoveEntityPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}