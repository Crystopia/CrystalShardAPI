package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket

class Shard_ClientboundEntityEventPacket : IPacket<ClientboundEntityEventPacketData> {
    override fun createPacket(packetObj: ClientboundEntityEventPacketData): ClientboundEntityEventPacket {
        return ClientboundEntityEventPacket(
            packetObj.entity,
            packetObj.status
        )
    }
}