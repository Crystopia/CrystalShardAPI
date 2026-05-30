package net.crystopia.crystalshard.paper.dhl.types.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInputEntry
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInputMultilineOptions
import net.kyori.adventure.text.Component

abstract class DialogInput<T : Any>(open var id: String)

data class DialogBooleanInput(
    override var id: String,
    var label: Component,
    var initial: Boolean,
    var onTrue: String,
    var onFalse: String
) : DialogInput<DialogBooleanInput>(id)

data class DialogNumberRangeInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelFormat: String,
    var start: Float,
    var end: Float,
    var initial: Float?,
    var step: Float?
) : DialogInput<DialogNumberRangeInput>(id)

data class DialogSingleOptionInput(
    override var id: String,
    var width: Int,
    var entries: MutableList<net.crystopia.crystalshard.paper.dhl.types.dialog.input.DialogSingleOptionInputEntry>,
    var label: Component,
    var labelVisible: Boolean
) : DialogInput<DialogSingleOptionInput>(id)

data class DialogTextInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelVisible: Boolean,
    var initial: String,
    var maxLength: Int,
    var multiline: DialogTextInputMultilineOptions
) : DialogInput<DialogTextInput>(id)

fun DialogInput<*>.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogInput<*> {
    return when (this) {
        is DialogBooleanInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput(
            id = this.id,
            label = PaperAdventure.asVanilla(this.label),
            initial = this.initial,
            onTrue = this.onTrue,
            onFalse = this.onFalse
        )

        is DialogNumberRangeInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput(
            id = this.id,
            width = this.width,
            label = PaperAdventure.asVanilla(this.label),
            labelFormat = this.labelFormat,
            start = this.start,
            end = this.end,
            initial = this.initial,
            step = this.step
        )

        is DialogSingleOptionInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput(
            id = this.id,
            width = this.width,
            entries = this.entries.map {
                DialogSingleOptionInputEntry(
                    id = it.id,
                    display = PaperAdventure.asVanilla(it.display),
                    initial = it.initial,
                )
            }.toMutableList(),
            label = PaperAdventure.asVanilla(this.label),
            labelVisible = this.labelVisible
        )

        is DialogTextInput -> net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput(
            id = this.id,
            width = this.width,
            label = PaperAdventure.asVanilla(this.label),
            labelVisible = this.labelVisible,
            initial = this.initial,
            maxLength = this.maxLength,
            multiline = DialogTextInputMultilineOptions(
                maxLines = this.multiline.maxLines,
                height = this.multiline.height
            )
        )

        else -> throw IllegalArgumentException("Unknown input type ${this.javaClass}")
    }
}