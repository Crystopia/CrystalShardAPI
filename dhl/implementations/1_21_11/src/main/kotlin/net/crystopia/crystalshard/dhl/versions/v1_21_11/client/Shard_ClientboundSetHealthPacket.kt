package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetHealthPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket

class Shard_ClientboundSetHealthPacket : IClientPacket<ClientboundSetHealthPacketData> {

    override fun createPacket(
        packetObj: ClientboundSetHealthPacketData
    ): ClientboundSetHealthPacket {
       return ClientboundSetHealthPacket(
            packetObj.health, packetObj.food, packetObj.saturation
        )
    }
}