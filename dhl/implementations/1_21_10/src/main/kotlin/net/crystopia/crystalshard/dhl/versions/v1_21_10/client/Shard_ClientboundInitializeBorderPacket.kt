package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundBorderPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket

class Shard_ClientboundInitializeBorderPacket : IPacket<ClientboundBorderPacketData> {

    override fun createPacket(
        packetObj: ClientboundBorderPacketData
    ): ClientboundInitializeBorderPacket {

        return ClientboundInitializeBorderPacket(
            packetObj.border.build()
        )
    }
}