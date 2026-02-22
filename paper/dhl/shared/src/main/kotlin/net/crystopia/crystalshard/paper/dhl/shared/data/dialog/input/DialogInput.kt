package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input

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
    var entries: MutableList<DialogSingleOptionInputEntry>,
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