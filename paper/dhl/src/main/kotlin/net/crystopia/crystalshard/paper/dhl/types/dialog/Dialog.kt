package net.crystopia.crystalshard.paper.dhl.types.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInputEntry
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInputMultilineOptions
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.toDhl
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogBooleanInput
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogNumberRangeInput
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogTextInput
import org.bukkit.craftbukkit.inventory.CraftItemStack

abstract class Dialog<T : Any>

data class ServerLinksDialog(
    var common: net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData,
    var exitAction: ActionButton?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<ServerLinksDialog>()

data class NoticeDialog(
    var common: net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData,
    var action: ActionButton
) : Dialog<NoticeDialog>()

data class MultiActionDialog(
    var common: net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData,
    var actions: MutableList<ActionButton>,
    var exitAction: ActionButton,
    var columns: Int,
) : Dialog<MultiActionDialog>()

data class DialogListDialog(
    var common: net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData,
    var dialogs: MutableSet<Dialog<*>>,
    var exitAction: ActionButton?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<DialogListDialog>()

data class ConfirmationDialog(
    var common: net.crystopia.crystalshard.paper.dhl.types.dialog.CommonDialogData,
    var yesButton: ActionButton,
    var noButton: ActionButton,
) : Dialog<ConfirmationDialog>()

fun Dialog<*>.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.Dialog<*> {
    return when (val dialog = this) {
        is ConfirmationDialog -> {
            net.crystopia.crystalshard.dhl.shared.data.dialog.ConfirmationDialog(
                common = dialog.common.toDhl(),
                yesButton = dialog.yesButton.toDhl(),
                noButton = dialog.noButton.toDhl()
            )
        }

        is DialogListDialog -> {
            val d = dialog as DialogListDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.DialogListDialog(
                common = d.common.toDhl(),
                dialogs = d.dialogs.map { dialog ->
                    dialog.toDhl()
                }.toMutableSet(),
                exitAction = d.exitAction?.toDhl(),
                columns = d.columns,
                buttonWidth = d.buttonWidth
            )
        }

        is ServerLinksDialog -> {
            val d = dialog as ServerLinksDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.ServerLinksDialog(
                common = d.common.toDhl(),
                exitAction = d.exitAction?.toDhl(),
                columns = d.columns,
                buttonWidth = d.buttonWidth
            )
        }

        is NoticeDialog -> {
            val d = dialog as NoticeDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.NoticeDialog(
                common = d.common.toDhl(),
                action = d.action.toDhl()
            )
        }

        is MultiActionDialog -> {
            val d = dialog as MultiActionDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.MultiActionDialog(
                common = d.common.toDhl(),
                actions = d.actions.map { btn ->
                    btn.toDhl()
                }.toMutableList(),
                exitAction = d.exitAction.toDhl(),
                columns = d.columns
            )
        }

        else -> {
            throw IllegalArgumentException("Unknown action type")
        }
    }
}