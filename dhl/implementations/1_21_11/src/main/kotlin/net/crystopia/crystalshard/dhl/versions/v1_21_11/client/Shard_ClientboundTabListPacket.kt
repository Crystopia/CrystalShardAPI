package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTabListPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundTabListPacket

class Shard_ClientboundTabListPacket : IClientPacket<ClientboundTabListPacketData> {

    override fun createPacket(
        packetObj: ClientboundTabListPacketData
    ): ClientboundTabListPacket {
        return ClientboundTabListPacket(
            packetObj.header,
            packetObj.footer
        )
    }
}