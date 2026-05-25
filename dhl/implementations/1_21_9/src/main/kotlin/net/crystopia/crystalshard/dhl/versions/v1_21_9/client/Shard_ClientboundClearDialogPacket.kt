package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.common.ClientboundClearDialogPacket

class Shard_ClientboundClearDialogPacket : IPacket<Any> {

    override fun createPacket(
        packetObj: Any
    ): ClientboundClearDialogPacket {
        return ClientboundClearDialogPacket.INSTANCE
    }
}