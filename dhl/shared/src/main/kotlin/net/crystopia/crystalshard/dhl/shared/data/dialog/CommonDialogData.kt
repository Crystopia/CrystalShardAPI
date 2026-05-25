package net.crystopia.crystalshard.dhl.shared.data.dialog

import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogInput
import net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction
import net.minecraft.network.chat.Component

data class CommonDialogData(
    var title: Component,
    var externalTitle: Component?,
    var canCloseWithEscape: Boolean,
    var pause: Boolean,
    var afterAction: DialogAction,
    var body: MutableList<DialogBody<*>>,
    var inputs: MutableList<DialogInput<*>>
)