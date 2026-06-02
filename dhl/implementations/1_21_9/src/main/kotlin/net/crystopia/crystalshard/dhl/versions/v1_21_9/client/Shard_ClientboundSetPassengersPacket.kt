package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import com.google.common.collect.ImmutableList
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket

class Shard_ClientboundSetPassengersPacket : IClientPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        val bukkitPassengers = packetObj.passengers.map { entity ->
            entity
        }
        val list = ImmutableList.copyOf(bukkitPassengers)
        packetObj.entity.passengers = list
        return ClientboundSetPassengersPacket(packetObj.entity)
    }
}