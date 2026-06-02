package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundContainerSetSlotPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket

class Shard_ClientboundContainerSetSlotPacket : IClientPacket<ClientboundContainerSetSlotPacketData> {

    override fun createPacket(
        packetObj: ClientboundContainerSetSlotPacketData
    ): ClientboundContainerSetSlotPacket {
        return ClientboundContainerSetSlotPacket(
            packetObj.id,
            packetObj.revision,
            packetObj.slot,
            packetObj.item
        )
    }
}