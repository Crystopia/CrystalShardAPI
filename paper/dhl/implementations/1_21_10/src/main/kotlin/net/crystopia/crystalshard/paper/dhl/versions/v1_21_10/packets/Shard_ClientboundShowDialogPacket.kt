package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.ConfirmationDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.DialogListDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.MultiActionDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.NoticeDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.ServerLinksDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundShowDialogPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog.build
import net.minecraft.core.Holder
import net.minecraft.network.protocol.common.ClientboundShowDialogPacket

class Shard_ClientboundShowDialogPacket : IPacket<ClientboundShowDialogPacketData> {

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