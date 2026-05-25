package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveVehiclePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.world.build
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