package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import com.google.common.collect.ImmutableList
import net.crystopia.crystalshard.paper.dhl.shared.data.packetsid.ClientboundSetPassengersPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetPassengersPacket
import org.bukkit.craftbukkit.entity.CraftEntity

class Shard_ClientboundSetPassengersPacket : IPacket<ClientboundSetPassengersPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetPassengersPacketData
    ): ClientboundSetPassengersPacket {
        val eventy = (packetObj.entity as CraftEntity).handle

        val bukkitPassengers = packetObj.passengers.map { entity ->
            (entity as CraftEntity).handle
        }
        val list = ImmutableList.copyOf(bukkitPassengers)

        eventy.passengers = list
        return ClientboundSetPassengersPacket(eventy)
    }
}