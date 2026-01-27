package net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.input.InputControl
import java.util.*

abstract class DialogInput<T : Any>(open var id: String)

data class BooleanInput(
    override var id: String,
    var label: Component,
    var initial: Boolean,
    var onTrue: String,
    var onFalse: String
) : DialogInput<BooleanInput>(id)

data class NumberRangeInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelFormat: String,
    var start: Float,
    var end: Float,
    var initial: Float?,
    var step: Float?
) : DialogInput<NumberRangeInput>(id)

data class SingleOptionInput(
    override var id: String,
    var width: Int,
    var entries: MutableList<SingleOptionInputEntry>,
    var label: Component,
    var labelVisible: Boolean
) : DialogInput<SingleOptionInput>(id)

data class TextInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelVisible: Boolean,
    var initial: String,
    var maxLength: Int,
    var multiline: TextInputMultilineOptions
) : DialogInput<TextInput>(id)