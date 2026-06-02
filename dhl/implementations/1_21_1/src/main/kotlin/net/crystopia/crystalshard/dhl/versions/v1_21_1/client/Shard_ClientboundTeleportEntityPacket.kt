package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket

class Shard_ClientboundTeleportEntityPacket : IPacket<ClientboundTeleportEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {
        packetObj.entity.setPos(packetObj.location.x, packetObj.location.y, packetObj.location.z)
        packetObj.entity.yRot = packetObj.location.yaw
        packetObj.entity.xRot = packetObj.location.pitch
        packetObj.entity.onGround = packetObj.onGround

        return ClientboundTeleportEntityPacket(packetObj.entity)
    }
}