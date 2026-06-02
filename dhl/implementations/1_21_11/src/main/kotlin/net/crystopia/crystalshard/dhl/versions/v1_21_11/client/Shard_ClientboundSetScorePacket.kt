package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundSetScorePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundSetScorePacket
import java.util.*

class Shard_ClientboundSetScorePacket : IClientPacket<ClientboundSetScorePacketData> {

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