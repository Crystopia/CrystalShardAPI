package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import com.google.common.collect.ImmutableList
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket

class Shard_ClientboundSetPassengersPacket : IPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        val entity = (packetObj.entity)
        val bukkitPassengers = packetObj.passengers.map { entity ->
            entity
        }
        val list = ImmutableList.copyOf(bukkitPassengers)

        entity.passengers = list
        return ClientboundSetPassengersPacket(entity)
    }
}