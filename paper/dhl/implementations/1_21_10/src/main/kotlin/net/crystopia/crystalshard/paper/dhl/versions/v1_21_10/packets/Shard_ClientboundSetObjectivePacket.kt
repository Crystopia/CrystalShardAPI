package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
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