package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.packets.lerpSteps
import net.minecraft.network.protocol.game.ClientboundMoveMinecartPacket

class Shard_ClientboundMoveMinecartPacket : IClientPacket<ClientboundMoveMinecartPacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveMinecartPacketData
    ): ClientboundMoveMinecartPacket {
        return ClientboundMoveMinecartPacket(
            packetObj.entity.id,
            packetObj.lerpSteps()
        )
    }
}