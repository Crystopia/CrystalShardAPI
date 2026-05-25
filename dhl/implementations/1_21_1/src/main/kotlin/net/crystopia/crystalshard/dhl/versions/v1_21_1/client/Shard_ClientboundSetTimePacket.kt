package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetTimePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
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