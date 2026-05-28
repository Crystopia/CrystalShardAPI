package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveEntityPacketData
import net.crystopia.crystalshard.dhl.shared.enums.entities.EntityMoveMode
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.minecraft.network.protocol.common.CommonPacketTypes
import net.minecraft.network.protocol.configuration.ConfigurationPacketTypes
import net.minecraft.network.protocol.cookie.CookiePacketTypes
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket
import net.minecraft.network.protocol.game.GamePacketTypes
import net.minecraft.network.protocol.handshake.HandshakePacketTypes
import net.minecraft.network.protocol.login.LoginPacketTypes
import net.minecraft.network.protocol.ping.PingPacketTypes
import net.minecraft.network.protocol.status.StatusPacketTypes
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
    callback: (packet: Shard_Packet<ClientboundMoveEntityPacketData>) -> Unit
): Shard_Packet<ClientboundMoveEntityPacketData> {

    val data = ClientboundMoveEntityPacketData(
        mode, (entity as CraftEntity).handle, xa, ya, za, yRot, xRot, onGround, hasRot, hasPos
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.moveEntity(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.moveEntity(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundMoveEntityPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}