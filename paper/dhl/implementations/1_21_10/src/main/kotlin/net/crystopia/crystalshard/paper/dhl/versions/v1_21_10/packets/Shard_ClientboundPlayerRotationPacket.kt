package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerRotationPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerRotationPacket

class Shard_ClientboundPlayerRotationPacket : IPacket<ClientboundPlayerRotationPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerRotationPacketData
    ): ClientboundPlayerRotationPacket {
        return ClientboundPlayerRotationPacket(
            packetObj.yRot,
            packetObj.relativeY,
            packetObj.xRot,
            packetObj.relativeX
        )
    }
}