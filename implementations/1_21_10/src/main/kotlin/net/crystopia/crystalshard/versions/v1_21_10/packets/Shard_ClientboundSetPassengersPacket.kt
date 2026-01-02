package net.crystopia.crystalshard.versions.v1_21_10.packets


import net.crystopia.crystalshard.shared.data.packets.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket

class Shard_ClientboundSetPassengersPacket : IPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        return ClientboundSetPassengersPacket(packetObj.entity)
    }
}