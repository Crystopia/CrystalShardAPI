package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetCarriedItemPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetCursorItemPacket

class Shard_ClientboundSetCarriedItemPacket : IPacket<ClientboundSetCarriedItemPacketData> {
    override fun createPacket(packetObj: ClientboundSetCarriedItemPacketData): ClientboundSetCursorItemPacket {
        return ClientboundSetCursorItemPacket(
            packetObj.item
        )
    }
}