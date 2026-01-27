package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.dialog.input.SingleOptionInputEntry
import net.minecraft.network.chat.Component
import net.minecraft.server.dialog.input.SingleOptionInput
import java.util.Optional

fun SingleOptionInputEntry.build(): SingleOptionInput.Entry {

    var mcComponent: Component? = null
    if (display != null) mcComponent = PaperAdventure.asVanilla(display)

    return SingleOptionInput.Entry(
        id,
        Optional.ofNullable(mcComponent),
        initial
    )
}