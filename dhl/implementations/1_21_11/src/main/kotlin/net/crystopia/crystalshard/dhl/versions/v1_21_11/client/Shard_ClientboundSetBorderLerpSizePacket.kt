package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.world.build
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket

class Shard_ClientboundSetBorderLerpSizePacket : IClientPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundSetBorderLerpSizePacket {
        return ClientboundSetBorderLerpSizePacket(
            packetObj.border.build()
        )
    }
}