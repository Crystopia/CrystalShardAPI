package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client



import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket

class Shard_ClientboundSetPlayerTeamPacket : IPacket<ClientboundSetPlayerTeamPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPlayerTeamPacketData
    ): ClientboundSetPlayerTeamPacket {
        return packetObj.build()
    }
}