package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDistancePacket

class Shard_ClientboundSetBorderWarningDistancePacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderWarningDistancePacket {
        return ClientboundSetBorderWarningDistancePacket(
            packetObj.border.build()
        )
    }
}