package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.dialog.Dialog

data class ClientboundShowDialogPacketData(
var dialog: Dialog<*>
)
