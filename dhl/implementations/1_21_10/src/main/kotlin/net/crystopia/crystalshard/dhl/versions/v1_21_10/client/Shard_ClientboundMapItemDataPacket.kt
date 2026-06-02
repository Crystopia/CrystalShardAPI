package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.paper.dhl.converter.v1_21_10.converter.data.maps.build
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket
import net.minecraft.world.level.saveddata.maps.MapDecoration
import net.minecraft.world.level.saveddata.maps.MapId

class Shard_ClientboundMapItemDataPacket : IClientPacket<ClientboundMapItemDataPacketData> {

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