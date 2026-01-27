package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.BooleanInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.NumberRangeInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.SingleOptionInput
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.TextInput
import java.util.Optional

internal fun BooleanInput.build(): net.minecraft.server.dialog.input.BooleanInput {
    return net.minecraft.server.dialog.input.BooleanInput(
        PaperAdventure.asVanilla(label), initial, onTrue, onFalse
    )
}

internal fun NumberRangeInput.build(): net.minecraft.server.dialog.input.NumberRangeInput {
    return net.minecraft.server.dialog.input.NumberRangeInput(
        width,
        PaperAdventure.asVanilla(label),
        labelFormat,
        net.minecraft.server.dialog.input.NumberRangeInput.RangeInfo(
            start, end, Optional.ofNullable(initial), Optional.ofNullable(step)
        )
    )
}

internal fun SingleOptionInput.build(): net.minecraft.server.dialog.input.SingleOptionInput {
    val list = mutableListOf<net.minecraft.server.dialog.input.SingleOptionInput.Entry>()
    entries.forEach { entry -> list.add(entry.build()) }

    return net.minecraft.server.dialog.input.SingleOptionInput(
        width,
        list,
        PaperAdventure.asVanilla(label),
        labelVisible
    )
}

internal fun TextInput.build(): net.minecraft.server.dialog.input.TextInput {

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