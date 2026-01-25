package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundShowDialogPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.core.Holder
import net.minecraft.network.protocol.common.ClientboundShowDialogPacket

class Shard_ClientboundShowDialogPacket : IPacket<ClientboundShowDialogPacketData> {

    override fun createPacket(
        packetObj: ClientboundShowDialogPacketData
    ): ClientboundShowDialogPacket {
        return ClientboundShowDialogPacket(
            Holder.direct(packetObj.dialog.build()!!)
        )
    }
}