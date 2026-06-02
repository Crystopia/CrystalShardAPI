package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket

class Shard_ClientboundInitializeBorderPacket : IClientPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundInitializeBorderPacket {
        return ClientboundInitializeBorderPacket(
            packetObj.border.build()
        )
    }
}