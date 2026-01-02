package net.crystopia.crystalshard.versions.v1_21_1.packets


import it.unimi.dsi.fastutil.ints.IntArrayList
import net.crystopia.crystalshard.shared.data.packets.ClientboundRemoveEntitiesPacketData
import net.crystopia.crystalshard.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket

class Shard_ClientboundRemoveEntitiesPacket : IPacket<ClientboundRemoveEntitiesPacketData> {

    override fun createPacket(
        packetObj: ClientboundRemoveEntitiesPacketData
    ): ClientboundRemoveEntitiesPacket {

        val ids = IntArrayList(packetObj.entityIds.size)
        packetObj.entityIds.forEach { i -> ids.add(i) }

        return ClientboundRemoveEntitiesPacket(ids)
    }
}