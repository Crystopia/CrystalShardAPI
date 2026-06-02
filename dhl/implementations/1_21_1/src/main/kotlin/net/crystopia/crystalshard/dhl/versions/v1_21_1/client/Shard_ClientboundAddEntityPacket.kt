package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.world.phys.Vec3

class Shard_ClientboundAddEntityPacket :
    net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket<net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAddEntityPacketData> {

    override fun createPacket(
        packetObj: net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundAddEntityPacketData
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