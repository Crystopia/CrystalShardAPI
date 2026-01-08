package net.crystopia.crystalshard.paper.versions.v1_21_1.packets



import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket

class Shard_ClientboundAddEntityPacket : IPacket<ClientboundAddEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundAddEntityPacketData
    ): ClientboundAddEntityPacket {
        return ClientboundAddEntityPacket(
            packetObj.entityId,
            packetObj.entityUUID,
            packetObj.location.x,
            packetObj.location.y,
            packetObj.location.y,
            packetObj.location.pitch,
            packetObj.location.yaw,
            packetObj.entityType,
            packetObj.data,
            packetObj.deltaMovement,
            packetObj.yHeadRot
        )
    }
}