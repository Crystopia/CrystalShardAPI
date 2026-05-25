package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockDestructionPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket

class Shard_ClientboundBlockDestructionPacket : IPacket<ClientboundBlockDestructionPacketData> {
    override fun createPacket(packetObj: ClientboundBlockDestructionPacketData): ClientboundBlockDestructionPacket {
        return ClientboundBlockDestructionPacket(
            packetObj.entityId,
            BlockPos(packetObj.pos.x,packetObj.pos.y,packetObj.pos.z),
            packetObj.progress
        )
    }
}