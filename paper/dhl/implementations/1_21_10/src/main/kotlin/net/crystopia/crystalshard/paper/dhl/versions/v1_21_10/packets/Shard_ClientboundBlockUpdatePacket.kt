package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket

class Shard_ClientboundBlockUpdatePacket : IPacket<ClientboundBlockUpdatePacketData> {

    override fun createPacket(packetObj: ClientboundBlockUpdatePacketData): ClientboundBlockUpdatePacket {
        return ClientboundBlockUpdatePacket(
            packetObj.blockPos,
            packetObj.state
        )
    }

}