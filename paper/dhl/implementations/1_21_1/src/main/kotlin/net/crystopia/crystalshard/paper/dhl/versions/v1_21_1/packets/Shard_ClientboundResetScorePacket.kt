package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
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