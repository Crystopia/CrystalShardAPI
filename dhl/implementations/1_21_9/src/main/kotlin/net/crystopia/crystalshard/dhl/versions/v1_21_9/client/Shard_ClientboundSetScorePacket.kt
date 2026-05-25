package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetScorePacket
import java.util.*

class Shard_ClientboundSetScorePacket : IPacket<ClientboundSetScorePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetScorePacketData
    ): ClientboundSetScorePacket {
        return ClientboundSetScorePacket(
            packetObj.score.ownerName,
            packetObj.score.displayId,
            packetObj.score.score,
            Optional.of(packetObj.score.displayName),
            Optional.of(packetObj.build())
        )
    }
}