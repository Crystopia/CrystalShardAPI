package net.crystopia.crystalshard.dhl.versions.v1_21_1.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket

class Shard_ClientboundSetPlayerTeamPacket : IPacket<ClientboundSetPlayerTeamPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPlayerTeamPacketData
    ): ClientboundSetPlayerTeamPacket {
        return packetObj.build()
    }
}