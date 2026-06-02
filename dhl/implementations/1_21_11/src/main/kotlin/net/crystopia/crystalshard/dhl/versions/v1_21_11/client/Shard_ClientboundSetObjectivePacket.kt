package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket

class Shard_ClientboundSetObjectivePacket : IClientPacket<ClientboundSetDisplayObjectivePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDisplayObjectivePacketData
    ): ClientboundSetObjectivePacket {
        return ClientboundSetObjectivePacket(
            packetObj.build(), packetObj.mode.id
        )
    }
}