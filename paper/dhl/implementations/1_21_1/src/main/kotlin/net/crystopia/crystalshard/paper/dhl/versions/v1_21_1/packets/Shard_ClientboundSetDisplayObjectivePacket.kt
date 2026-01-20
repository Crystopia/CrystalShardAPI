package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket

class Shard_ClientboundSetDisplayObjectivePacket : IPacket<ClientboundSetDisplayObjectivePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDisplayObjectivePacketData
    ): ClientboundSetDisplayObjectivePacket {
        return ClientboundSetDisplayObjectivePacket(
            packetObj.displaySlot.id, packetObj.build()
        )
    }
}