package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveVehiclePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundMoveVehiclePacket

class Shard_ClientboundMoveVehiclePacket : IPacket<ClientboundMoveVehiclePacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveVehiclePacketData
    ): ClientboundMoveVehiclePacket {
        return ClientboundMoveVehiclePacket(
            packetObj.position.build(),
            packetObj.yRot,
            packetObj.xRot
        )
    }
}