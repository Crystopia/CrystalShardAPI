package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveVehiclePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket

class Shard_ClientboundMoveVehiclePacket : IPacket<ClientboundMoveVehiclePacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveVehiclePacketData
    ): ClientboundMoveVehiclePacket {
        packetObj.entity.setPos(
            packetObj.position.x,
            packetObj.position.y,
            packetObj.position.z
        )
        packetObj.entity.xRot = packetObj.yRot
        packetObj.entity.yRot = packetObj.yRot

        return ClientboundMoveVehiclePacket(
            packetObj.entity
        )
    }
}