package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.common.ClientboundClearDialogPacket

class Shard_ClientboundClearDialogPacket : IClientPacket<Any> {

    override fun createPacket(
        packetObj: Any
    ): ClientboundClearDialogPacket {
        return ClientboundClearDialogPacket.INSTANCE
    }
}