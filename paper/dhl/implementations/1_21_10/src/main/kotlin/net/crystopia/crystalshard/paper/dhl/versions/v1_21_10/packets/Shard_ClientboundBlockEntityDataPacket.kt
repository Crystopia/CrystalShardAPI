package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBlockEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket

class Shard_ClientboundBlockEntityDataPacket : IPacket<ClientboundBlockEntityDataPacketData> {

    override fun createPacket(packetObj: ClientboundBlockEntityDataPacketData): ClientboundBlockEntityDataPacket {
        return ClientboundBlockEntityDataPacket(
            packetObj.blockPos,
            packetObj.type,
            packetObj.nbt
        )
    }

}