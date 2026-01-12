package net.crystopia.crystalshard.paper.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundBlockDestructionPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket

class Shard_ClientboundBlockDestructionPacket : IPacket<ClientboundBlockDestructionPacketData> {
    override fun createPacket(packetObj: ClientboundBlockDestructionPacketData): ClientboundBlockDestructionPacket {
        return ClientboundBlockDestructionPacket(
            packetObj.entityId,
            packetObj.pos,
            packetObj.progress
        )
    }
}