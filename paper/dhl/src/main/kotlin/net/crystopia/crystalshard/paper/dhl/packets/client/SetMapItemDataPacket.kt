package net.crystopia.crystalshard.paper.dhl.packets.client

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.ClientPacketFactory
import net.crystopia.crystalshard.dhl.shared.ClientPacket
import net.crystopia.crystalshard.dhl.shared.data.maps.MapDecoration
import net.crystopia.crystalshard.dhl.shared.data.maps.MapPatch
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.dhl.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.dhl.shared.exceptions.NoPacketMethodFound
import net.crystopia.crystalshard.paper.dhl.utils.ServerUtil
import net.crystopia.crystalshard.dhl.versions.v1_21_11.general.ClientPacketBuilder

fun ClientPacketFactory.setMapItemData(
    mapId: Int,
    scale: Byte,
    locked: Boolean,
    decorations: MutableList<net.crystopia.crystalshard.paper.dhl.types.maps.MapDecoration>,
    colorPatch: MapPatch,
    callback: (packet: ClientPacket<ClientboundMapItemDataPacketData>) -> Unit
): ClientPacket<ClientboundMapItemDataPacketData> {

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
            ClientPacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_10 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_10.builder.ClientPacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_9 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_9.builder.ClientPacketBuilder.setMapItemData(
                data
            )
        }

        ServerVersion.v1_21_1 -> {
            net.crystopia.crystalshard.dhl.versions.v1_21_1.builder.ClientPacketBuilder.setMapItemData(
                data
            )
        }

        else -> {
            throw NoPacketMethodFound("${ServerUtil.currentVersion()}")
        }
    }

    val shardPacket = ClientPacket<ClientboundMapItemDataPacketData>()
    shardPacket.packetData = data
    shardPacket.packetObject = packet
    callback(shardPacket)
    return shardPacket
}