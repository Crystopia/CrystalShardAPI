package net.crystopia.crystalshard.paper.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket

class Shard_ClientboundOpenSignEditorPacket : IPacket<ClientboundOpenSignEditorPacketData> {

    override fun createPacket(packetObj: ClientboundOpenSignEditorPacketData): ClientboundOpenSignEditorPacket {
        return ClientboundOpenSignEditorPacket(
            packetObj.blockPos,
            packetObj.isFrontText
        )
    }

}