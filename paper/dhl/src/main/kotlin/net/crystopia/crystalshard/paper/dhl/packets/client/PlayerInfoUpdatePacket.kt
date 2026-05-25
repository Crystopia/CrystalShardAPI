package net.crystopia.crystalshard.paper.dhl.packets.client

import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerInfoUpdatePacketData
import net.crystopia.crystalshard.dhl.shared.enums.packets.InfoUpdateAction
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.minecraft.network.Connection
import net.minecraft.network.protocol.PacketFlow
import net.minecraft.server.level.ClientInformation
import net.minecraft.server.network.CommonListenerCookie
import net.minecraft.server.network.ServerGamePacketListenerImpl
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftServer
import org.bukkit.craftbukkit.entity.CraftPlayer
import org.bukkit.entity.Player

fun ClientPacketFactory.playerInfoUpdate(
    serverPlayer: Player,
    actions: MutableList<InfoUpdateAction>,
    callback: (packet: Shard_Packet<ClientboundPlayerInfoUpdatePacketData>) -> Unit
): Shard_Packet<ClientboundPlayerInfoUpdatePacketData> {

    val player = (serverPlayer as CraftPlayer).handle
    val data = ClientboundPlayerInfoUpdatePacketData(
        (serverPlayer as CraftPlayer).handle, actions
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.playerInfoUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.playerInfoUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.playerInfoUpdatePacket(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.playerInfoUpdatePacket(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }


    val shardPacket = Shard_Packet<ClientboundPlayerInfoUpdatePacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}