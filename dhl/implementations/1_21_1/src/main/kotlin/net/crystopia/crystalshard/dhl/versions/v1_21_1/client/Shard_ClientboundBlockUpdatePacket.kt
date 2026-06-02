package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket

class Shard_ClientboundBlockUpdatePacket :
    net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket<net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockUpdatePacketData> {

    override fun createPacket(packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBlockUpdatePacketData): ClientboundBlockUpdatePacket {
        return ClientboundBlockUpdatePacket(
            BlockPos(packetObj.blockPos.x, packetObj.blockPos.y, packetObj.blockPos.z),
            packetObj.block.defaultBlockState()
        )
    }

}