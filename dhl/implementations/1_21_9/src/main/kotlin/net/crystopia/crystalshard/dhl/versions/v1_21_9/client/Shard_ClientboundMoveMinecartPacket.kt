package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.packets.lerpSteps
import net.minecraft.network.protocol.game.ClientboundMoveMinecartPacket

class Shard_ClientboundMoveMinecartPacket : IPacket<ClientboundMoveMinecartPacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveMinecartPacketData
    ): ClientboundMoveMinecartPacket {
        return ClientboundMoveMinecartPacket(
            packetObj.entity.id,
            packetObj.lerpSteps()
        )
    }
}