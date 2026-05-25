package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.packets.build
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.enums.scoreboard.DisplaySlot
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