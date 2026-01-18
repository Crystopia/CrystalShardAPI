package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket

class Shard_ClientboundBlockEntityDataPacket : IPacket<ClientboundBlockEntityDataPacketData> {

    override fun createPacket(packetObj: ClientboundBlockEntityDataPacketData): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket(
            BlockPos(packetObj.blockPos.x,packetObj.blockPos.y,packetObj.blockPos.z),
            packetObj.type.type,
            packetObj.nbt
        )
    }

}