package net.crystopia.crystalshard.paper.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket

class Shard_ClientboundBlockUpdatePacket : IPacket<ClientboundBlockUpdatePacketData> {

    override fun createPacket(packetObj: ClientboundBlockUpdatePacketData): ClientboundBlockUpdatePacket {
        return ClientboundBlockUpdatePacket(
            packetObj.blockPos,
            packetObj.state
        )
    }

}