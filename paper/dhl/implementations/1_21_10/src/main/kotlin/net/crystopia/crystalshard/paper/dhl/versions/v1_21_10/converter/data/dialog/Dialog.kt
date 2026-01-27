package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog

import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.ConfirmationDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.DialogListDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.MultiActionDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.NoticeDialog
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.ServerLinksDialog
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.server.dialog.ActionButton
import net.minecraft.server.dialog.Dialog
import java.util.Optional

internal fun ServerLinksDialog.build(): net.minecraft.server.dialog.ServerLinksDialog {
    return net.minecraft.server.dialog.ServerLinksDialog(
        common.build(), Optional.ofNullable(exitAction?.build()), columns, buttonWidth
    )
}

internal fun NoticeDialog.build(): net.minecraft.server.dialog.NoticeDialog {
    return net.minecraft.server.dialog.NoticeDialog(
        common.build(), action.build()
    )
}

internal fun MultiActionDialog.build(): net.minecraft.server.dialog.MultiActionDialog {
    val mcActions = mutableListOf<ActionButton>()
    actions.forEach { button ->
        mcActions.add(button.build())
    }

    return net.minecraft.server.dialog.MultiActionDialog(
        common.build(), mcActions, Optional.ofNullable(exitAction?.build()), columns
    )
}

internal fun DialogListDialog.build(): net.minecraft.server.dialog.DialogListDialog {
    val mcDialogs = mutableListOf<Holder<Dialog>>()
    dialogs.forEach { dialog ->
        when (dialog) {
            is ConfirmationDialog -> mcDialogs.add(Holder.direct(dialog.build()))
            is DialogListDialog -> mcDialogs.add(Holder.direct(dialog.build()))
            is ServerLinksDialog -> mcDialogs.add(Holder.direct(dialog.build()))
            is NoticeDialog -> mcDialogs.add(Holder.direct(dialog.build()))
            is MultiActionDialog -> mcDialogs.add(Holder.direct(dialog.build()))
        }
    }

    return net.minecraft.server.dialog.DialogListDialog(
        common.build(), HolderSet.direct(mcDialogs), Optional.ofNullable(exitAction?.build()), columns, buttonWidth
    )
}

internal fun ConfirmationDialog.build(): net.minecraft.server.dialog.ConfirmationDialog {
    return net.minecraft.server.dialog.ConfirmationDialog(
        common.build(),
        yesButton.build(),
        noButton.build(),
    )
}