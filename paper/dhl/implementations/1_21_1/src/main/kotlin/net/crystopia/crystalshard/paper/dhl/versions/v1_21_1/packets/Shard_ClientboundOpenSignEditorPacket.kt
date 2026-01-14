package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket

class Shard_ClientboundOpenSignEditorPacket : IPacket<ClientboundOpenSignEditorPacketData> {

    override fun createPacket(packetObj: ClientboundOpenSignEditorPacketData): ClientboundOpenSignEditorPacket {
        return ClientboundOpenSignEditorPacket(
            packetObj.blockPos,
            packetObj.isFrontText
        )
    }

}