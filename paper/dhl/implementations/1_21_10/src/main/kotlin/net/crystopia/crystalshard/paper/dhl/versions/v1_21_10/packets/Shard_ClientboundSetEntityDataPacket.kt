package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket

class Shard_ClientboundSetEntityDataPacket : IPacket<ClientboundSetEntityDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetEntityDataPacketData
    ): ClientboundSetEntityDataPacket {
        return ClientboundSetEntityDataPacket(
            packetObj.entityId,
            packetObj.entityData
        )
    }
}