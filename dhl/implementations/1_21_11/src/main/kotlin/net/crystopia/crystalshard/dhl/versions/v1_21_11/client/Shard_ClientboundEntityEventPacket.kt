package net.crystopia.crystalshard.dhl.versions.v1_21_11.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket

class Shard_ClientboundEntityEventPacket : IClientPacket<ClientboundEntityEventPacketData> {
    override fun createPacket(packetObj: ClientboundEntityEventPacketData): ClientboundEntityEventPacket {
        return ClientboundEntityEventPacket(
            packetObj.entity,
            packetObj.status
        )
    }
}