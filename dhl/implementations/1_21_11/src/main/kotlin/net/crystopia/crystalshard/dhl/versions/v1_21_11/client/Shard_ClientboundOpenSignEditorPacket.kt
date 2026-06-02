package net.crystopia.crystalshard.dhl.versions.v1_21_11.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket

class Shard_ClientboundOpenSignEditorPacket : IClientPacket<ClientboundOpenSignEditorPacketData> {

    override fun createPacket(packetObj: ClientboundOpenSignEditorPacketData): ClientboundOpenSignEditorPacket {
        return ClientboundOpenSignEditorPacket(
            BlockPos(packetObj.blockPos.x,packetObj.blockPos.y,packetObj.blockPos.z),
            packetObj.isFrontText
        )
    }

}