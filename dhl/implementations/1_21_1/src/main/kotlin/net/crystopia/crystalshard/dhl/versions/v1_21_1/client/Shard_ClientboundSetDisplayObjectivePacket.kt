package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.packets.build
import net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.scoreboard.DisplaySlot
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket

class Shard_ClientboundSetDisplayObjectivePacket : IClientPacket<ClientboundSetDisplayObjectivePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetDisplayObjectivePacketData
    ): ClientboundSetDisplayObjectivePacket {
        return ClientboundSetDisplayObjectivePacket(
            DisplaySlot.convert(packetObj.displaySlot).id, packetObj.build()
        )
    }
}