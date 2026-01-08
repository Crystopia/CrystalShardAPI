package net.crystopia.crystalshard.paper.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket

class Shard_ClientboundSetPassengersPacket : IPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        return ClientboundSetPassengersPacket(packetObj.entity)
    }
}