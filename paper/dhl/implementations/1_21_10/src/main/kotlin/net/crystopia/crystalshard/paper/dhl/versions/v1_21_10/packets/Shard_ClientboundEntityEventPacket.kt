package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundEntityEventPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundEntityEventPacket

class Shard_ClientboundEntityEventPacket : IPacket<ClientboundEntityEventPacketData> {
    override fun createPacket(packetObj: ClientboundEntityEventPacketData): ClientboundEntityEventPacket {
        return ClientboundEntityEventPacket(
            packetObj.entity,
            packetObj.status
        )
    }
}