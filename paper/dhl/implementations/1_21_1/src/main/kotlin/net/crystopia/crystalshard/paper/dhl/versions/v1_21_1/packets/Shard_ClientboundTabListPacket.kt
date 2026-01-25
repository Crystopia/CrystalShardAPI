package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundTabListPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTabListPacket

class Shard_ClientboundTabListPacket : IPacket<ClientboundTabListPacketData> {

    override fun createPacket(
        packetObj: ClientboundTabListPacketData
    ): ClientboundTabListPacket {
        return ClientboundTabListPacket(
            PaperAdventure.asVanilla(packetObj.header),
            PaperAdventure.asVanilla(packetObj.footer)
        )
    }
}