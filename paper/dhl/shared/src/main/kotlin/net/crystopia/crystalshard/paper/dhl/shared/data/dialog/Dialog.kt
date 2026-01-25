package net.crystopia.crystalshard.paper.dhl.shared.data.dialog

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.ActionButton
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import java.util.*

abstract class Dialog<T : Any> {
    open fun build(): net.minecraft.server.dialog.Dialog? {
        return null
    }
}

data class ServerLinksDialog(
    var common: CommonDialogData,
    var exitAction: ActionButton<*>?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<ServerLinksDialog>() {
    override fun build(): net.minecraft.server.dialog.ServerLinksDialog {
        return net.minecraft.server.dialog.ServerLinksDialog(
            common.build(),
            Optional.ofNullable(exitAction?.build()),
            columns,
            buttonWidth
        )
    }
}

data class NoticeDialog(
    var common: CommonDialogData,
    var action: ActionButton<*>
) : Dialog<NoticeDialog>() {
    override fun build(): net.minecraft.server.dialog.NoticeDialog {
        return net.minecraft.server.dialog.NoticeDialog(
            common.build(),
            action.build()
        )
    }
}

data class MultiActionDialog(
    var common: CommonDialogData,
    var actions: MutableList<ActionButton<*>>,
    var exitAction: ActionButton<*>?,
    var columns: Int,
) : Dialog<MultiActionDialog>() {
    override fun build(): net.minecraft.server.dialog.MultiActionDialog {
        val mcActions = mutableListOf<net.minecraft.server.dialog.ActionButton>()
        actions.forEach { button ->
            mcActions.add(button.build())
        }

        return net.minecraft.server.dialog.MultiActionDialog(
            common.build(),
            mcActions,
            Optional.ofNullable(exitAction?.build()),
            columns
        )
    }
}

data class DialogListDialog(
    var common: CommonDialogData,
    var dialogs: MutableSet<Dialog<*>>,
    var exitAction: ActionButton<*>?,
    var columns: Int,
    var buttonWidth: Int
) : Dialog<DialogListDialog>() {
    override fun build(): net.minecraft.server.dialog.DialogListDialog {
        val mcDialogs = mutableListOf<Holder<net.minecraft.server.dialog.Dialog>>()
        dialogs.forEach { dialog ->
            mcDialogs.add(Holder.direct(dialog.build()!!))
        }

        return net.minecraft.server.dialog.DialogListDialog(
            common.build(),
            HolderSet.direct(mcDialogs),
            Optional.ofNullable(exitAction?.build()),
            columns,
            buttonWidth

        )
    }
}

data class ConfirmationDialog(
    var common: CommonDialogData,
    var yesButton: ActionButton<*>,
    var noButton: ActionButton<*>,
) : Dialog<ConfirmationDialog>() {
    override fun build(): net.minecraft.server.dialog.ConfirmationDialog {
        return net.minecraft.server.dialog.ConfirmationDialog(
            common.build(),
            yesButton.build(),
            noButton.build(),

            )
    }
}