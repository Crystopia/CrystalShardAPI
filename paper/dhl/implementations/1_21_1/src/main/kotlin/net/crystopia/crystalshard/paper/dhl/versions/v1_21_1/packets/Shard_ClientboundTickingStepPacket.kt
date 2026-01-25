package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundTickingStepPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
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