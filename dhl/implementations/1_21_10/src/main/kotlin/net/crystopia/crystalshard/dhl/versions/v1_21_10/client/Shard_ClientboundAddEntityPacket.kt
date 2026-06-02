package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAddEntityPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.world.phys.Vec3

class Shard_ClientboundAddEntityPacket : IClientPacket<ClientboundAddEntityPacketData> {

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
            Vec3.ZERO,
            packetObj.yHeadRot
        )
    }
}