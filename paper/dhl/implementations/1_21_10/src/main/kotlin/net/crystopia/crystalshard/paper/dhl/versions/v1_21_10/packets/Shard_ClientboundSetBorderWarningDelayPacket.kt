package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundBorderPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetBorderWarningDelayPacket

class Shard_ClientboundSetBorderWarningDelayPacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderWarningDelayPacket {
        return ClientboundSetBorderWarningDelayPacket(
            packetObj.border.build()
        )
    }
}