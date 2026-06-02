package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundCooldownPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundCooldownPacket
import net.minecraft.resources.Identifier

class Shard_ClientboundCooldownPacket : IClientPacket<ClientboundCooldownPacketData> {

    override fun createPacket(
        packetObj: ClientboundCooldownPacketData
    ): ClientboundCooldownPacket {
        println(packetObj.item.toString())
        return ClientboundCooldownPacket(
            Identifier.parse(packetObj.item.toString().split(" ")[1]),
            packetObj.duration
        )
    }
}