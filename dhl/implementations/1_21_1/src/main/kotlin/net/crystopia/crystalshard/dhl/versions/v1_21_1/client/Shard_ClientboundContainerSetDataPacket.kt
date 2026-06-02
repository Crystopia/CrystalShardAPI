package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerSetDataPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundContainerSetDataPacket

class Shard_ClientboundContainerSetDataPacket : IClientPacket<ClientboundContainerSetDataPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetDataPacketData
    ): ClientboundContainerSetDataPacket {
        return ClientboundContainerSetDataPacket(
            packetObj.id,
            packetObj.property.toInt(),
            packetObj.value.toInt()
        )
    }
}