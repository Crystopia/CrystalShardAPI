package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.minecraft.network.protocol.game.ClientboundAnimatePacket

class Shard_ClientboundAnimatePacket :
    net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket<net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAnimatePacketData> {
    override fun createPacket(packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAnimatePacketData): ClientboundAnimatePacket {
        return ClientboundAnimatePacket(
            packetObj.entity,
            packetObj.animationId
        )
    }
}