package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
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