package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundBlockDestructionPacket

class Shard_ClientboundBlockDestructionPacket :
    net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket<net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockDestructionPacketData> {
    override fun createPacket(packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockDestructionPacketData): ClientboundBlockDestructionPacket {
        return ClientboundBlockDestructionPacket(
            packetObj.entityId,
            BlockPos(packetObj.pos.x, packetObj.pos.y, packetObj.pos.z),
            packetObj.progress
        )
    }
}