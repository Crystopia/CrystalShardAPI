package net.crystopia.crystalshard.paper.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
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