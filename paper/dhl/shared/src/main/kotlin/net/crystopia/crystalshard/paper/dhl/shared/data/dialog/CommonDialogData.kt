package net.crystopia.crystalshard.paper.dhl.shared.data.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.DialogInput
import net.crystopia.crystalshard.paper.dhl.shared.enums.dialog.DialogAction
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.CommonDialogData
import net.minecraft.server.dialog.Input
import net.minecraft.server.dialog.input.InputControl
import java.util.*

data class CommonDialogData(
    var title: Component,
    var externalTitle: Component?,
    var canCloseWithEscape: Boolean,
    var pause: Boolean,
    var afterAction: DialogAction,
    var body: MutableList<DialogBody<*>>,
    var inputs: MutableList<DialogInput<*>>
)