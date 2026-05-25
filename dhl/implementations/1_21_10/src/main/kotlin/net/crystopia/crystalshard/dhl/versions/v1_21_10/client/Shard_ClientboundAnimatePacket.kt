package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAnimatePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket

class Shard_ClientboundAnimatePacket : IPacket<ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: ClientboundAnimatePacketData): ClientboundAnimatePacket {
        return ClientboundAnimatePacket(
            packetObj.entity,
            packetObj.animationId
        )
    }
}