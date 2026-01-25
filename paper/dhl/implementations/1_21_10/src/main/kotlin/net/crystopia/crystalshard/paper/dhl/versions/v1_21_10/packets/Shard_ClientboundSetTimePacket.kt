package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetTimePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundTickingStepPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetTimePacket

class Shard_ClientboundSetTimePacket : IPacket<ClientboundSetTimePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetTimePacketData
    ): ClientboundSetTimePacket {
        return ClientboundSetTimePacket(
            packetObj.gameTime,
            packetObj.dayTime,
            packetObj.tickDayTime
        )
    }
}