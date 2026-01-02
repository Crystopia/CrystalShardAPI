package net.crystopia.crystalshard.versions.v1_21_10.packets


import net.crystopia.crystalshard.shared.data.packets.ClientboundSetEntityDataPacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
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