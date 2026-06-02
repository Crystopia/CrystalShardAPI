package net.crystopia.crystalshard.dhl.versions.v1_21_11.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAnimatePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundAnimatePacket

class Shard_ClientboundAnimatePacket : IClientPacket<ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: ClientboundAnimatePacketData): ClientboundAnimatePacket {
        return ClientboundAnimatePacket(
            packetObj.entity,
            packetObj.animationId
        )
    }
}