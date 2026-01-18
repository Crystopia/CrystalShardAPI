package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets



import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.world.phys.Vec3

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
            packetObj.entityType.type,
            packetObj.data,
            Vec3.ZERO,
            packetObj.yHeadRot
        )
    }
}