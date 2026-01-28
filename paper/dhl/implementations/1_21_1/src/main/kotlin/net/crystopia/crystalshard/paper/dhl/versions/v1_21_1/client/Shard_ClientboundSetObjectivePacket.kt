package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.packets.build
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket

class Shard_ClientboundSetObjectivePacket : IPacket<ClientboundSetDisplayObjectivePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDisplayObjectivePacketData
    ): ClientboundSetObjectivePacket {
        return ClientboundSetObjectivePacket(
            packetObj.build(), packetObj.mode.id
        )
    }
}