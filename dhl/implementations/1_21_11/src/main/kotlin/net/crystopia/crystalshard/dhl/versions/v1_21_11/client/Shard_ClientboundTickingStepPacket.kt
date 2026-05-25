package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTickingStepPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTickingStepPacket

class Shard_ClientboundTickingStepPacket : IPacket<ClientboundTickingStepPacketData> {

    override fun createPacket(
        packetObj: ClientboundTickingStepPacketData
    ): ClientboundTickingStepPacket {
        return ClientboundTickingStepPacket(
            packetObj.tickSteps
        )
    }
}