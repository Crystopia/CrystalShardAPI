package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetCarriedItemPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket

class Shard_ClientboundSetCarriedItemPacket : IPacket<ClientboundSetCarriedItemPacketData> {
    override fun createPacket(packetObj: ClientboundSetCarriedItemPacketData): ClientboundSetCarriedItemPacket {
        return ClientboundSetCarriedItemPacket(
            packetObj.slot
        )
    }
}