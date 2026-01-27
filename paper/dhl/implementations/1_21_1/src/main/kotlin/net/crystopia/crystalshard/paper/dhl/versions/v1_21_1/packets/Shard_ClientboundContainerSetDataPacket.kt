package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundContainerSetDataPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundContainerSetDataPacket

class Shard_ClientboundContainerSetDataPacket : IPacket<ClientboundContainerSetDataPacketData> {

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