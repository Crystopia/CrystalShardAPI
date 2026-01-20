package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetDisplayObjectivePacketData
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetScorePacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket
import net.minecraft.network.protocol.game.ClientboundSetScorePacket
import java.util.Optional

class Shard_ClientboundSetScorePacket : IPacket<ClientboundSetScorePacketData> {

    override fun createPacket(
        packetObj: ClientboundSetScorePacketData
    ): ClientboundSetScorePacket {
        return ClientboundSetScorePacket(
            packetObj.score.ownerName,
            packetObj.score.displayId,
            packetObj.score.score,
            Optional.of(PaperAdventure.asVanilla(packetObj.score.displayName)),
            Optional.of(packetObj.build())
        )
    }
}