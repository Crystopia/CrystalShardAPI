package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.dialog.input

import net.crystopia.crystalshard.dhl.shared.data.dialog.input.DialogSingleOptionInputEntry
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.input.SingleOptionInput
import java.util.*

fun DialogSingleOptionInputEntry.build(): SingleOptionInput.Entry {

    var mcComponent: Component? = null
    if (display != null) mcComponent = display

    return SingleOptionInput.Entry(
        id,
        Optional.ofNullable(mcComponent),
        initial
    )
}