package net.crystopia.crystalshard.paper.dhl.types.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogInput
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.toDhl
import net.kyori.adventure.text.Component

data class CommonDialogData(
    var title: Component,
    var externalTitle: Component?,
    var canCloseWithEscape: Boolean,
    var pause: Boolean,
    var afterAction: net.crystopia.crystalshard.dhl.shared.enums.dialog.DialogAction,
    var body: MutableList<DialogBody<*>>,
    var inputs: MutableList<DialogInput<*>>
)

fun CommonDialogData.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData {
    return net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData(
        title = PaperAdventure.asVanilla(this.title),
        externalTitle = PaperAdventure.asVanilla(this.title),
        canCloseWithEscape = this.canCloseWithEscape,
        pause = this.pause,
        afterAction = this.afterAction,
        body = this.body.map { body ->
            body.toDhl()
        }.toMutableList(),
        inputs = this.inputs.map { input ->
            input.toDhl()
        }.toMutableList()
    )
}