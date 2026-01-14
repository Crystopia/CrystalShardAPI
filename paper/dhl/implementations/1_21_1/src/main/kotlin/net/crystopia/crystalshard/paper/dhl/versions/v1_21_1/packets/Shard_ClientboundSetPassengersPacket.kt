package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket

class Shard_ClientboundSetPassengersPacket : IPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        return ClientboundSetPassengersPacket(packetObj.entity)
    }
}