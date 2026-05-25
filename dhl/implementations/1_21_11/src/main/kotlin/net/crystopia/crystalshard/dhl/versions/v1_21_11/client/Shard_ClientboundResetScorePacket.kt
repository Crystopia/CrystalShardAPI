package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundResetScorePacket

class Shard_ClientboundResetScorePacket : IPacket<ClientboundSetScorePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetScorePacketData
    ): ClientboundResetScorePacket {
        return ClientboundResetScorePacket(
            packetObj.score.ownerName,
            packetObj.score.displayId,
        )
    }
}