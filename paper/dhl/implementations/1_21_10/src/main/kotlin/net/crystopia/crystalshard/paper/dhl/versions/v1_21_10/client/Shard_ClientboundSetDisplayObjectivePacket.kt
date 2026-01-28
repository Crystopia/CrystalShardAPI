package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets.build
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.scoreboard.DisplaySlot
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket

class Shard_ClientboundSetDisplayObjectivePacket : IPacket<ClientboundSetDisplayObjectivePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDisplayObjectivePacketData
    ): ClientboundSetDisplayObjectivePacket {
        return ClientboundSetDisplayObjectivePacket(
            DisplaySlot.convert(packetObj.displaySlot).id, packetObj.build()
        )
    }
}