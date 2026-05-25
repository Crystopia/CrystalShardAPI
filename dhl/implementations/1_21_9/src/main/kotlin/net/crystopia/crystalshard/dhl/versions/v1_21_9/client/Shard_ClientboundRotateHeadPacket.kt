package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket

class Shard_ClientboundRotateHeadPacket : IPacket<ClientboundRotateHeadPacketData> {

    override fun createPacket(
        packetObj: ClientboundRotateHeadPacketData
    ): ClientboundRotateHeadPacket {
        val angelMultiplier = 256F / 360F

        return ClientboundRotateHeadPacket(
            packetObj.entity, ((packetObj.yaw * angelMultiplier).toInt().toByte())
        )
    }
}