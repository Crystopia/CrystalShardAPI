package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.Shard_Packet
import net.crystopia.crystalshard.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.dhl.shared.data.maps.MapPatch
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.PacketBuilder

fun ClientPacketFactory.setMapItemData(
    mapId: Int,
    scale: Byte,
    locked: Boolean,
    decorations: MutableList<net.crystopia.crystalshard.paper.dhl.types.maps.MapDecoration>,
    colorPatch: MapPatch,
    callback: (packet: Shard_Packet<ClientboundMapItemDataPacketData>) -> Unit
): Shard_Packet<ClientboundMapItemDataPacketData> {

    val data = ClientboundMapItemDataPacketData(
        mapId, scale, locked, decorations.map {
            MapDecoration(
                type = it.type,
                x = it.x,
                y = it.y,
                rot = it.rot,
                name = PaperAdventure.asVanilla(it.name)
            )
        }.toMutableList(), colorPatch
    )

    val packet = when (ServerUtil.currentVersion()) {
        ServerVersion.v1_21_11 -> {
            PacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.general.PacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.general.PacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.general.PacketBuilder.setMapItemData(
                data
            )
        }

        else -> {
            throw IllegalArgumentException("Unsupported server version: ${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = Shard_Packet<ClientboundMapItemDataPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}