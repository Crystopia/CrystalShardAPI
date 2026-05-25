package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundCooldownPacket
import net.minecraft.resources.ResourceLocation

class Shard_ClientboundCooldownPacket : IPacket<ClientboundCooldownPacketData> {

    override fun createPacket(
        packetObj: ClientboundCooldownPacketData
    ): ClientboundCooldownPacket {
        return ClientboundCooldownPacket(
            ResourceLocation.parse(packetObj.item.toString()),
            packetObj.duration
        )
    }
}