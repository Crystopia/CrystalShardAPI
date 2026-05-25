package net.crystopia.crystalshard.dhl.versions.v1_21_9.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundOpenSignEditorPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.BlockPos
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket

class Shard_ClientboundOpenSignEditorPacket : IPacket<ClientboundOpenSignEditorPacketData> {

    override fun createPacket(packetObj: ClientboundOpenSignEditorPacketData): ClientboundOpenSignEditorPacket {
        return ClientboundOpenSignEditorPacket(
            BlockPos(packetObj.blockPos.x,packetObj.blockPos.y,packetObj.blockPos.z),
            packetObj.isFrontText
        )
    }

}