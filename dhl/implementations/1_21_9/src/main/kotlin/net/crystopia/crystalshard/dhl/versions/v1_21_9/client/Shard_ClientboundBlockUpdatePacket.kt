package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockUpdatePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket

class Shard_ClientboundBlockUpdatePacket : IPacket<ClientboundBlockUpdatePacketData> {

    override fun createPacket(packetObj: ClientboundBlockUpdatePacketData): ClientboundBlockUpdatePacket {
        return ClientboundBlockUpdatePacket(
            BlockPos(packetObj.blockPos.x, packetObj.blockPos.y, packetObj.blockPos.z),
            packetObj.block.defaultBlockState()
        )
    }

}