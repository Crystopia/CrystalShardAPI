package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundTickingStatePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTickingStatePacket

class Shard_ClientboundTickingStatePacket : IPacket<ClientboundTickingStatePacketData> {

    override fun createPacket(
        packetObj: ClientboundTickingStatePacketData
    ): ClientboundTickingStatePacket {
        return ClientboundTickingStatePacket(
            packetObj.tickRate,
            packetObj.isFrozen
        )
    }
}