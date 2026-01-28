package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMapItemDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets.colorPatch
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets.decorations
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket
import net.minecraft.world.level.saveddata.maps.MapId

class Shard_ClientboundMapItemDataPacket : IPacket<ClientboundMapItemDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundMapItemDataPacketData
    ): ClientboundMapItemDataPacket {
        return ClientboundMapItemDataPacket(
            MapId(packetObj.mapId),
            packetObj.scale,
            packetObj.locked,
            packetObj.decorations(),
            packetObj.colorPatch()
        )
    }
}