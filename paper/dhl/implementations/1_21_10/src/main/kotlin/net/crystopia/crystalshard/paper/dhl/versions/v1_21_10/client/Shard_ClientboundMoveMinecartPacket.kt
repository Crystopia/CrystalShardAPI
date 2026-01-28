package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets.lerpSteps
import net.minecraft.network.protocol.game.ClientboundMoveMinecartPacket

class Shard_ClientboundMoveMinecartPacket : IPacket<ClientboundMoveMinecartPacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveMinecartPacketData
    ): ClientboundMoveMinecartPacket {
        return ClientboundMoveMinecartPacket(
            packetObj.entityId,
            packetObj.lerpSteps()
        )
    }
}