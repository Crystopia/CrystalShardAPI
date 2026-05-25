package net.crystopia.crystalshard.dhl.shared.data.dialog

import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton

abstract class Dialog<T : Any>

data class ServerLinksDialog(
    var common: CommonDialogData,
    var exitAction: ActionButton?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<ServerLinksDialog>()

data class NoticeDialog(
    var common: CommonDialogData,
    var action: ActionButton
) : Dialog<NoticeDialog>()

data class MultiActionDialog(
    var common: CommonDialogData,
    var actions: MutableList<ActionButton>,
    var exitAction: ActionButton?,
    var columns: Int,
) : Dialog<MultiActionDialog>()

data class DialogListDialog(
    var common: CommonDialogData,
    var dialogs: MutableSet<Dialog<*>>,
    var exitAction: ActionButton?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<DialogListDialog>()

data class ConfirmationDialog(
    var common: CommonDialogData,
    var yesButton: ActionButton,
    var noButton: ActionButton,
) : Dialog<ConfirmationDialog>()