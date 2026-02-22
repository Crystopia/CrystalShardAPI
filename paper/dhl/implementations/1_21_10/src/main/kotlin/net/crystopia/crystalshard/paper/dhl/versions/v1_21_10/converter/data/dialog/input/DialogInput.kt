package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.DialogBooleanInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.DialogNumberRangeInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.DialogSingleOptionInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.DialogTextInput
import java.util.Optional

internal fun DialogBooleanInput.build(): net.minecraft.server.dialog.input.BooleanInput {
    return net.minecraft.server.dialog.input.BooleanInput(
        PaperAdventure.asVanilla(label), initial, onTrue, onFalse
    )
}

internal fun DialogNumberRangeInput.build(): net.minecraft.server.dialog.input.NumberRangeInput {
    return net.minecraft.server.dialog.input.NumberRangeInput(
        width,
        PaperAdventure.asVanilla(label),
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
        PaperAdventure.asVanilla(label),
        labelVisible
    )
}

internal fun DialogTextInput.build(): net.minecraft.server.dialog.input.TextInput {

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