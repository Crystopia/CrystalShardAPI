package net.crystopia.crystalshard.paper.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundCooldownPacket
import net.minecraft.resources.Identifier

class Shard_ClientboundCooldownPacket : IPacket<ClientboundCooldownPacketData> {

    override fun createPacket(
        packetObj: ClientboundCooldownPacketData
    ): ClientboundCooldownPacket {
        return ClientboundCooldownPacket(
            Identifier.parse(packetObj.item.key.toString()),
            packetObj.duration
        )
    }
}