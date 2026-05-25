package net.crystopia.crystalshard.paper.dhl.types.dialog

import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogInput
import net.kyori.adventure.text.Component
import java.util.*

data class CommonDialogData(
    var title: Component,
    var externalTitle: Component?,
    var canCloseWithEscape: Boolean,
    var pause: Boolean,
    var afterAction: net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction,
    var body: MutableList<DialogBody<*>>,
    var inputs: MutableList<DialogInput<*>>
)