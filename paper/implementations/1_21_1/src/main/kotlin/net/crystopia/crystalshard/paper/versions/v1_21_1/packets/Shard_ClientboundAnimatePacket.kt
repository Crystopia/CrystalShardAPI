package net.crystopia.crystalshard.paper.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundAnimatePacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket

class Shard_ClientboundAnimatePacket : IPacket<ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: ClientboundAnimatePacketData): ClientboundAnimatePacket {
        return ClientboundAnimatePacket(
            packetObj.entity,
            packetObj.animationId
        )
    }
}