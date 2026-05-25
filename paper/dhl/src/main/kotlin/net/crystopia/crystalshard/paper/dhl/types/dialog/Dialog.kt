package net.crystopia.crystalshard.paper.dhl.types.dialog

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.shared.data.custom.NamespacedKey
import net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData
import net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.CommonButtonData
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInputEntry
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInputMultilineOptions
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionButton
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionCustomAll
import net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogBooleanInput
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogNumberRangeInput
import net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInput
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
    val dialog = this
    return when (dialog) {
        is ConfirmationDialog -> {
            val d = dialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.ConfirmationDialog(
                common = net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData(
                    title = PaperAdventure.asVanilla(d.common.title),
                    externalTitle = PaperAdventure.asVanilla(d.common.title),
                    canCloseWithEscape = d.common.canCloseWithEscape,
                    pause = d.common.pause,
                    afterAction = d.common.afterAction,
                    body = d.common.body.map { body ->
                        when (body) {
                            is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                contents = PaperAdventure.asVanilla(body.contents),
                                width = body.width
                            )

                            is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
                                item = CraftItemStack.asNMSCopy(body.item),
                                description = if (body.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                    contents = PaperAdventure.asVanilla(body.description!!.contents),
                                    width = body.description?.width ?: 0
                                ) else null,
                                showDecorations = body.showDecorations,
                                showTooltip = body.showTooltip,
                                width = body.width,
                                height = body.height
                            )

                            else -> throw IllegalArgumentException("Unknown body type ${body.javaClass}")
                        }
                    }.toMutableList(),
                    inputs = d.common.inputs.map { input ->
                        when (input) {
                            is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
                                id = input.id,
                                label = PaperAdventure.asVanilla(input.label),
                                initial = input.initial,
                                onTrue = input.onTrue,
                                onFalse = input.onFalse
                            )

                            is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelFormat = input.labelFormat,
                                start = input.start,
                                end = input.end,
                                initial = input.initial,
                                step = input.step
                            )

                            is DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
                                id = input.id,
                                width = input.width,
                                entries = input.entries.map {
                                    DialogSingleOptionInputEntry(
                                        id = it.id,
                                        display = PaperAdventure.asVanilla(it.display),
                                        initial = it.initial,
                                    )
                                }.toMutableList(),
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible
                            )

                            is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible,
                                initial = input.initial,
                                maxLength = input.maxLength,
                                multiline = DialogTextInputMultilineOptions(
                                    maxLines = input.multiline.maxLines,
                                    height = input.multiline.height
                                )
                            )

                            else -> throw IllegalArgumentException("Unknown input type ${input.javaClass}")
                        }
                    }.toMutableList()
                ),
                yesButton = net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                    button = CommonButtonData(
                        label = PaperAdventure.asVanilla(d.yesButton.button.label),
                        tooltip = PaperAdventure.asVanilla(d.yesButton.button.tooltip),
                        width = d.yesButton.button.width,
                    ),
                    action = when (d.yesButton.action) {

                        is ActionCustomAll -> {

                            net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (d.yesButton.action as ActionCustomAll).id.namespace,
                                    (d.yesButton.action as ActionCustomAll).id.key
                                ), additionsNBT = (d.yesButton.action as ActionCustomAll).additionsNBT
                            )
                        }

                        is ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                            type = (d.noButton.action as ActionStaticAction<*>).type,
                            value = (d.noButton.action as ActionStaticAction<*>).value
                        )

                        else -> throw IllegalArgumentException("Unknown action type")
                    }
                ),
                noButton = net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                    button = CommonButtonData(
                        label = PaperAdventure.asVanilla(d.noButton.button.label),
                        tooltip = PaperAdventure.asVanilla(d.noButton.button.tooltip),
                        width = d.noButton.button.width,
                    ),
                    action = when (d.noButton.action) {
                        is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                            id = NamespacedKey(
                                (d.noButton.action as ActionCustomAll).id.namespace,
                                (d.noButton.action as ActionCustomAll).id.key
                            ), additionsNBT = (d.noButton.action as ActionCustomAll).additionsNBT
                        )

                        is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                            type = (d.noButton.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                            value = (d.noButton.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                        )

                        else -> throw IllegalArgumentException("Unknown action type")
                    }
                )
            )
        }

        is DialogListDialog -> {
            val d = dialog as DialogListDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.DialogListDialog(
                common = net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData(
                    title = PaperAdventure.asVanilla(d.common.title),
                    externalTitle = PaperAdventure.asVanilla(d.common.title),
                    canCloseWithEscape = d.common.canCloseWithEscape,
                    pause = d.common.pause,
                    afterAction = d.common.afterAction,
                    body = d.common.body.map { body ->
                        when (body) {
                            is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                contents = PaperAdventure.asVanilla(body.contents),
                                width = body.width
                            )

                            is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
                                item = CraftItemStack.asNMSCopy(body.item),
                                description = if (body.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                    contents = PaperAdventure.asVanilla(body.description!!.contents),
                                    width = body.description?.width ?: 0
                                ) else null,
                                showDecorations = body.showDecorations,
                                showTooltip = body.showTooltip,
                                width = body.width,
                                height = body.height
                            )

                            else -> throw IllegalArgumentException("Unknown body type ${body.javaClass}")
                        }
                    }.toMutableList(),
                    inputs = d.common.inputs.map { input ->
                        when (input) {
                            is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
                                id = input.id,
                                label = PaperAdventure.asVanilla(input.label),
                                initial = input.initial,
                                onTrue = input.onTrue,
                                onFalse = input.onFalse
                            )

                            is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelFormat = input.labelFormat,
                                start = input.start,
                                end = input.end,
                                initial = input.initial,
                                step = input.step
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
                                id = input.id,
                                width = input.width,
                                entries = input.entries.map {
                                    DialogSingleOptionInputEntry(
                                        id = it.id,
                                        display = PaperAdventure.asVanilla(it.display),
                                        initial = it.initial,
                                    )
                                }.toMutableList(),
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible
                            )

                            is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible,
                                initial = input.initial,
                                maxLength = input.maxLength,
                                multiline = DialogTextInputMultilineOptions(
                                    maxLines = input.multiline.maxLines,
                                    height = input.multiline.height
                                )
                            )

                            else -> throw IllegalArgumentException("Unknown input type ${input.javaClass}")
                        }
                    }.toMutableList()
                ),
                dialogs = d.dialogs.map { dialog ->
                    dialog.toDhl()
                }.toMutableSet(),
                exitAction = d.exitAction?.let { btn ->
                    net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                        button = CommonButtonData(
                            label = PaperAdventure.asVanilla(btn.button.label),
                            tooltip = PaperAdventure.asVanilla(btn.button.tooltip),
                            width = btn.button.width,
                        ),
                        action = when (btn.action) {
                            is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (btn.action as ActionCustomAll).id.namespace,
                                    (btn.action as ActionCustomAll).id.key
                                ), additionsNBT = (btn.action as ActionCustomAll).additionsNBT
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                                type = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                                value = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                            )

                            else -> throw IllegalArgumentException("Unknown action type")
                        }
                    )
                },
                columns = d.columns,
                buttonWidth = d.buttonWidth
            )
        }

        is ServerLinksDialog -> {
            val d = dialog as ServerLinksDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.ServerLinksDialog(
                common = net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData(
                    title = PaperAdventure.asVanilla(d.common.title),
                    externalTitle = PaperAdventure.asVanilla(d.common.title),
                    canCloseWithEscape = d.common.canCloseWithEscape,
                    pause = d.common.pause,
                    afterAction = d.common.afterAction,
                    body = d.common.body.map { body ->
                        when (body) {
                            is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                contents = PaperAdventure.asVanilla(body.contents),
                                width = body.width
                            )

                            is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
                                item = CraftItemStack.asNMSCopy(body.item),
                                description = if (body.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                    contents = PaperAdventure.asVanilla(body.description!!.contents),
                                    width = body.description?.width ?: 0
                                ) else null,
                                showDecorations = body.showDecorations,
                                showTooltip = body.showTooltip,
                                width = body.width,
                                height = body.height
                            )

                            else -> throw IllegalArgumentException("Unknown body type ${body.javaClass}")
                        }
                    }.toMutableList(),
                    inputs = d.common.inputs.map { input ->
                        when (input) {
                            is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
                                id = input.id,
                                label = PaperAdventure.asVanilla(input.label),
                                initial = input.initial,
                                onTrue = input.onTrue,
                                onFalse = input.onFalse
                            )

                            is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelFormat = input.labelFormat,
                                start = input.start,
                                end = input.end,
                                initial = input.initial,
                                step = input.step
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
                                id = input.id,
                                width = input.width,
                                entries = input.entries.map {
                                    DialogSingleOptionInputEntry(
                                        id = it.id,
                                        display = PaperAdventure.asVanilla(it.display),
                                        initial = it.initial,
                                    )
                                }.toMutableList(),
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible
                            )

                            is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible,
                                initial = input.initial,
                                maxLength = input.maxLength,
                                multiline = DialogTextInputMultilineOptions(
                                    maxLines = input.multiline.maxLines,
                                    height = input.multiline.height
                                )
                            )

                            else -> throw IllegalArgumentException("Unknown input type ${input.javaClass}")
                        }
                    }.toMutableList()
                ),
                exitAction = d.exitAction?.let { btn ->
                    net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                        button = CommonButtonData(
                            label = PaperAdventure.asVanilla(btn.button.label),
                            tooltip = PaperAdventure.asVanilla(btn.button.tooltip),
                            width = btn.button.width,
                        ),
                        action = when (btn.action) {
                            is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (btn.action as ActionCustomAll).id.namespace,
                                    (btn.action as ActionCustomAll).id.key
                                ), additionsNBT = (btn.action as ActionCustomAll).additionsNBT
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                                type = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                                value = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                            )

                            else -> throw IllegalArgumentException("Unknown action type")
                        }
                    )
                },
                columns = d.columns,
                buttonWidth = d.buttonWidth
            )
        }

        is NoticeDialog -> {
            val d = dialog as NoticeDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.NoticeDialog(
                common = net.crystopia.crystalshard.dhl.shared.data.dialog.CommonDialogData(
                    title = PaperAdventure.asVanilla(d.common.title),
                    externalTitle = PaperAdventure.asVanilla(d.common.title),
                    canCloseWithEscape = d.common.canCloseWithEscape,
                    pause = d.common.pause,
                    afterAction = d.common.afterAction,
                    body = d.common.body.map { body ->
                        when (body) {
                            is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                contents = PaperAdventure.asVanilla(body.contents),
                                width = body.width
                            )

                            is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
                                item = CraftItemStack.asNMSCopy(body.item),
                                description = if (body.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                    contents = PaperAdventure.asVanilla(body.description!!.contents),
                                    width = body.description?.width ?: 0
                                ) else null,
                                showDecorations = body.showDecorations,
                                showTooltip = body.showTooltip,
                                width = body.width,
                                height = body.height
                            )

                            else -> throw IllegalArgumentException("Unknown body type ${body.javaClass}")
                        }
                    }.toMutableList(),
                    inputs = d.common.inputs.map { input ->
                        when (input) {
                            is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
                                id = input.id,
                                label = PaperAdventure.asVanilla(input.label),
                                initial = input.initial,
                                onTrue = input.onTrue,
                                onFalse = input.onFalse
                            )

                            is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelFormat = input.labelFormat,
                                start = input.start,
                                end = input.end,
                                initial = input.initial,
                                step = input.step
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
                                id = input.id,
                                width = input.width,
                                entries = input.entries.map {
                                    DialogSingleOptionInputEntry(
                                        id = it.id,
                                        display = PaperAdventure.asVanilla(it.display),
                                        initial = it.initial,
                                    )
                                }.toMutableList(),
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible
                            )

                            is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible,
                                initial = input.initial,
                                maxLength = input.maxLength,
                                multiline = DialogTextInputMultilineOptions(
                                    maxLines = input.multiline.maxLines,
                                    height = input.multiline.height
                                )
                            )

                            else -> throw IllegalArgumentException("Unknown input type ${input.javaClass}")
                        }
                    }.toMutableList()
                ),
                action = d.action.let { btn ->
                    net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                        button = CommonButtonData(
                            label = PaperAdventure.asVanilla(btn.button.label),
                            tooltip = PaperAdventure.asVanilla(btn.button.tooltip),
                            width = btn.button.width,
                        ),
                        action = when (btn.action) {
                            is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (btn.action as ActionCustomAll).id.namespace,
                                    (btn.action as ActionCustomAll).id.key
                                ), additionsNBT = (btn.action as ActionCustomAll).additionsNBT
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                                type = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                                value = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                            )

                            else -> throw IllegalArgumentException("Unknown action type")
                        }
                    )
                }
            )
        }

        is MultiActionDialog -> {
            val d = dialog as MultiActionDialog
            net.crystopia.crystalshard.dhl.shared.data.dialog.MultiActionDialog(
                common = CommonDialogData(
                    title = PaperAdventure.asVanilla(d.common.title),
                    externalTitle = PaperAdventure.asVanilla(d.common.title),
                    canCloseWithEscape = d.common.canCloseWithEscape,
                    pause = d.common.pause,
                    afterAction = d.common.afterAction,
                    body = d.common.body.map { body ->
                        when (body) {
                            is DialogBodyPlainMessage -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                contents = PaperAdventure.asVanilla(body.contents),
                                width = body.width
                            )

                            is DialogBodyItemBody -> net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyItemBody(
                                item = CraftItemStack.asNMSCopy(body.item),
                                description = if (body.description != null) net.crystopia.crystalshard.dhl.shared.data.dialog.DialogBodyPlainMessage(
                                    contents = PaperAdventure.asVanilla(body.description!!.contents),
                                    width = body.description?.width ?: 0
                                ) else null,
                                showDecorations = body.showDecorations,
                                showTooltip = body.showTooltip,
                                width = body.width,
                                height = body.height
                            )

                            else -> throw IllegalArgumentException("Unknown body type ${body.javaClass}")
                        }
                    }.toMutableList(),
                    inputs = d.common.inputs.map { input ->
                        when (input) {
                            is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
                                id = input.id,
                                label = PaperAdventure.asVanilla(input.label),
                                initial = input.initial,
                                onTrue = input.onTrue,
                                onFalse = input.onFalse
                            )

                            is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelFormat = input.labelFormat,
                                start = input.start,
                                end = input.end,
                                initial = input.initial,
                                step = input.step
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
                                id = input.id,
                                width = input.width,
                                entries = input.entries.map {
                                    DialogSingleOptionInputEntry(
                                        id = it.id,
                                        display = PaperAdventure.asVanilla(it.display),
                                        initial = it.initial,
                                    )
                                }.toMutableList(),
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible
                            )

                            is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
                                id = input.id,
                                width = input.width,
                                label = PaperAdventure.asVanilla(input.label),
                                labelVisible = input.labelVisible,
                                initial = input.initial,
                                maxLength = input.maxLength,
                                multiline = DialogTextInputMultilineOptions(
                                    maxLines = input.multiline.maxLines,
                                    height = input.multiline.height
                                )
                            )

                            else -> throw IllegalArgumentException("Unknown input type ${input.javaClass}")
                        }
                    }.toMutableList()
                ),
                actions = d.actions.map { btn ->
                    net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                        button = CommonButtonData(
                            label = PaperAdventure.asVanilla(btn.button.label),
                            tooltip = PaperAdventure.asVanilla(btn.button.tooltip),
                            width = btn.button.width,
                        ),
                        action = when (btn.action) {
                            is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (btn.action as ActionCustomAll).id.namespace,
                                    (btn.action as ActionCustomAll).id.key
                                ), additionsNBT = (btn.action as ActionCustomAll).additionsNBT
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                                type = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                                value = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                            )

                            else -> throw IllegalArgumentException("Unknown action type")
                        }
                    )
                }.toMutableList(),
                exitAction = d.exitAction.let { btn ->
                    net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionButton(
                        button = CommonButtonData(
                            label = PaperAdventure.asVanilla(btn.button.label),
                            tooltip = PaperAdventure.asVanilla(btn.button.tooltip),
                            width = btn.button.width,
                        ),
                        action = when (btn.action) {
                            is ActionCustomAll -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionCustomAll(
                                id = NamespacedKey(
                                    (btn.action as ActionCustomAll).id.namespace,
                                    (btn.action as ActionCustomAll).id.key
                                ), additionsNBT = (btn.action as ActionCustomAll).additionsNBT
                            )

                            is net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*> -> net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.ActionStaticAction(
                                type = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).type,
                                value = (btn.action as net.crystopia.crystalshard.paper.dhl.types.dialog.buttons.ActionStaticAction<*>).value
                            )

                            else -> throw IllegalArgumentException("Unknown action type")
                        }
                    )
                },
                columns = d.columns
            )
        }

        else -> {
            throw IllegalArgumentException("Unknown action type")
        }
    }
}