package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.dialog.input

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.input.SingleOptionInput
import java.util.*

data class SingleOptionInputEntry(
    var id: String,
    var display: Component?,
    var initial: Boolean
) {
    fun build(): SingleOptionInput.Entry {

        var mcComponent: net.minecraft.network.chat.Component? = null
        if (display != null) mcComponent = PaperAdventure.asVanilla(display)

        return SingleOptionInput.Entry(
            id,
            Optional.ofNullable(mcComponent),
            initial
        )
    }
}
