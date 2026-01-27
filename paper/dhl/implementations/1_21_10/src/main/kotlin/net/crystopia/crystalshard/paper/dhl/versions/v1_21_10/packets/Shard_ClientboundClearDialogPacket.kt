package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.common.ClientboundClearDialogPacket

class Shard_ClientboundClearDialogPacket : IPacket<Any> {

    override fun createPacket(
        packetObj: Any
    ): ClientboundClearDialogPacket {
        return ClientboundClearDialogPacket.INSTANCE
    }
}