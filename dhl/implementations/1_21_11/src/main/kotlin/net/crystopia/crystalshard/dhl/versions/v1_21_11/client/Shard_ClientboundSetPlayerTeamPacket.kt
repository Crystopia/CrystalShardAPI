package net.crystopia.crystalshard.dhl.versions.v1_21_11.client



import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPlayerTeamPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket

class Shard_ClientboundSetPlayerTeamPacket : IClientPacket<ClientboundSetPlayerTeamPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPlayerTeamPacketData
    ): ClientboundSetPlayerTeamPacket {
        return packetObj.build()
    }
}