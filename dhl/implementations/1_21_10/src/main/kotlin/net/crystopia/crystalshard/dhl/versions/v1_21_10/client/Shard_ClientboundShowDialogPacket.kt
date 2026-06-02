package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.dialog.*
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundShowDialogPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.dialog.build
import net.minecraft.core.Holder
import net.minecraft.network.protocol.common.ClientboundShowDialogPacket

class Shard_ClientboundShowDialogPacket : IClientPacket<ClientboundShowDialogPacketData> {

    override fun createPacket(
        packetObj: ClientboundShowDialogPacketData
    ): ClientboundShowDialogPacket {

        val dialog = when (packetObj.dialog) {
            is ConfirmationDialog ->((packetObj.dialog as ConfirmationDialog).build())
            is DialogListDialog -> ((packetObj.dialog as DialogListDialog).build())
            is ServerLinksDialog -> ((packetObj.dialog as ServerLinksDialog).build())
            is NoticeDialog -> ((packetObj.dialog as NoticeDialog).build())
            is MultiActionDialog -> ((packetObj.dialog as MultiActionDialog).build())
            else -> {throw Exception("Dialog Type not found.")
            }
        }

        return ClientboundShowDialogPacket(
            Holder.direct(dialog)
        )
    }
}