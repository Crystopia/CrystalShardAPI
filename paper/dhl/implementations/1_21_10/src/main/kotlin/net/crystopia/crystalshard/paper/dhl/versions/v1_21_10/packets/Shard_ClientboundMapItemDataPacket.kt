package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.maps.build
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket
import net.minecraft.world.level.saveddata.maps.MapDecoration
import net.minecraft.world.level.saveddata.maps.MapId

class Shard_ClientboundMapItemDataPacket : IPacket<ClientboundMapItemDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundMapItemDataPacketData
    ): ClientboundMapItemDataPacket {

        val decorations = mutableListOf<MapDecoration>()
        packetObj.decorations.forEach { decoration ->
            decorations.add(decoration.build())
        }

        return ClientboundMapItemDataPacket(
            MapId(packetObj.mapId),
            packetObj.scale,
            packetObj.locked,
            decorations,
            packetObj.colorPatch.build()
        )
    }
}