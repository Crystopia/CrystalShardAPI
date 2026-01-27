package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.Dialog

data class ClientboundShowDialogPacketData(
var dialog: Dialog<*>
)
