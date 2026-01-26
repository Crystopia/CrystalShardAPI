package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.input.InputControl
import java.util.*

abstract class DialogInput<T : Any>(open var id: String) {
    open fun build(): InputControl? {
        return null
    }
}

data class BooleanInput(
    override var id: String,
    var label: Component,
    var initial: Boolean,
    var onTrue: String,
    var onFalse: String
) : DialogInput<BooleanInput>(id) {
    override fun build(): net.minecraft.server.dialog.input.BooleanInput {
        return net.minecraft.server.dialog.input.BooleanInput(
            PaperAdventure.asVanilla(label),
            initial,
            onTrue,
            onFalse
        )
    }
}

data class NumberRangeInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelFormat: String,
    var start: Float,
    var end: Float,
    var initial: Float?,
    var step: Float?
) : DialogInput<NumberRangeInput>(id) {
    override fun build(): net.minecraft.server.dialog.input.NumberRangeInput {
        return net.minecraft.server.dialog.input.NumberRangeInput(
            width,
            PaperAdventure.asVanilla(label),
            labelFormat,
            net.minecraft.server.dialog.input.NumberRangeInput.RangeInfo(
                start, end, Optional.ofNullable(initial), Optional.ofNullable(step)
            )
        )
    }
}

data class SingleOptionInput(
    override var id: String,
    var width: Int,
    var entries: MutableList<SingleOptionInputEntry>,
    var label: Component,
    var labelVisible: Boolean
) : DialogInput<SingleOptionInput>(id) {
    override fun build(): net.minecraft.server.dialog.input.SingleOptionInput {
        val list = mutableListOf<net.minecraft.server.dialog.input.SingleOptionInput.Entry>()
        entries.forEach { entry -> list.add(entry.build()) }

        return net.minecraft.server.dialog.input.SingleOptionInput(
            width,
            list,
            PaperAdventure.asVanilla(label),
            labelVisible
        )
    }
}

data class TextInput(
    override var id: String,
    var width: Int,
    var label: Component,
    var labelVisible: Boolean,
    var initial: String,
    var maxLength: Int,
    var multiline: TextInputMultilineOptions
) : DialogInput<TextInput>(id) {
    override fun build(): net.minecraft.server.dialog.input.TextInput {

        return net.minecraft.server.dialog.input.TextInput(
        width,
            PaperAdventure.asVanilla(label),
            labelVisible,
            initial,
            maxLength,
            Optional.ofNullable(net.minecraft.server.dialog.input.TextInput.MultilineOptions(
                Optional.ofNullable(multiline.maxLines),
                Optional.ofNullable(multiline.height),
            ))
        )
    }
}