package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.Dialog

data class ClientboundShowDialogPacketData(
var dialog: Dialog<*>
)
