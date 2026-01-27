package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetHealthPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket

class Shard_ClientboundSetHealthPacket : IPacket<ClientboundSetHealthPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetHealthPacketData
    ): ClientboundSetHealthPacket {
       return ClientboundSetHealthPacket(
            packetObj.health, packetObj.food, packetObj.saturation
        )
    }
}