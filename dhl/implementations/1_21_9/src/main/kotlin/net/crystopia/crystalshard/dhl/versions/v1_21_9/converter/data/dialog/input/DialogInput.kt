package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.dialog.input

import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogBooleanInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogNumberRangeInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInput
import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogTextInput
import java.util.*

internal fun DialogBooleanInput.build(): net.minecraft.server.dialog.input.BooleanInput {
    return net.minecraft.server.dialog.input.BooleanInput(
        label, initial, onTrue, onFalse
    )
}

internal fun DialogNumberRangeInput.build(): net.minecraft.server.dialog.input.NumberRangeInput {
    return net.minecraft.server.dialog.input.NumberRangeInput(
        width,
        label,
        labelFormat,
        net.minecraft.server.dialog.input.NumberRangeInput.RangeInfo(
            start, end, Optional.ofNullable(initial), Optional.ofNullable(step)
        )
    )
}

internal fun DialogSingleOptionInput.build(): net.minecraft.server.dialog.input.SingleOptionInput {
    val list = mutableListOf<net.minecraft.server.dialog.input.SingleOptionInput.Entry>()
    entries.forEach { entry -> list.add(entry.build()) }

    return net.minecraft.server.dialog.input.SingleOptionInput(
        width,
        list,
        label,
        labelVisible
    )
}

internal fun DialogTextInput.build(): net.minecraft.server.dialog.input.TextInput {

    return net.minecraft.server.dialog.input.TextInput(
        width,
        label,
        labelVisible,
        initial,
        maxLength,
        Optional.ofNullable(net.minecraft.server.dialog.input.TextInput.MultilineOptions(
            Optional.ofNullable(multiline.maxLines),
            Optional.ofNullable(multiline.height),
        ))
    )
}