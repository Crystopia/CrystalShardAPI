package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetCarriedItemPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket

class Shard_ClientboundSetCarriedItemPacket : IClientPacket<ClientboundSetCarriedItemPacketData> {
    override fun createPacket(packetObj: ClientboundSetCarriedItemPacketData): ClientboundSetCarriedItemPacket {
        return ClientboundSetCarriedItemPacket(
            packetObj.slot
        )
    }
}