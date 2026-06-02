package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTickingStatePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundTickingStatePacket

class Shard_ClientboundTickingStatePacket : IClientPacket<ClientboundTickingStatePacketData> {

    override fun createPacket(
        packetObj: ClientboundTickingStatePacketData
    ): ClientboundTickingStatePacket {
        return ClientboundTickingStatePacket(
            packetObj.tickRate,
            packetObj.isFrozen
        )
    }
}