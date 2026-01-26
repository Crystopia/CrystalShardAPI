package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.dialog.buttons

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component
import net.minecraft.server.dialog.CommonButtonData
import java.util.*

data class CommonButtonData(
    var label: Component,
    var tooltip: Component?,
    var width: Int
) {
    fun build(): CommonButtonData {
        var mcTooltip: net.minecraft.network.chat.Component? = null
        if (tooltip != null) mcTooltip = PaperAdventure.asVanilla(tooltip)

        return CommonButtonData(
            PaperAdventure.asVanilla(label),
            Optional.ofNullable(mcTooltip),
            width
        )
    }
}
