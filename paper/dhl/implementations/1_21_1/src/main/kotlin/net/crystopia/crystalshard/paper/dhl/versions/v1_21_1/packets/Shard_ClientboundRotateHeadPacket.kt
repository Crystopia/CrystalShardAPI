package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundRotateHeadPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket

class Shard_ClientboundRotateHeadPacket : IPacket<ClientboundRotateHeadPacketData> {

    override fun createPacket(
        packetObj: ClientboundRotateHeadPacketData
    ): ClientboundRotateHeadPacket {

        val angleMultiplier = 256F / 360F

        return ClientboundRotateHeadPacket(
            packetObj.entity, ((packetObj.yaw * angleMultiplier).toInt().toByte())
        )
    }
}