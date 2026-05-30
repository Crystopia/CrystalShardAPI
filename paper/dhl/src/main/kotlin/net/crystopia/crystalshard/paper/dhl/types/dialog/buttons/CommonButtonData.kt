package net.crystopia.crystalshard.paper.dhl.types.dialog.buttons

import io.papermc.paper.adventure.PaperAdventure
import net.kyori.adventure.text.Component

data class CommonButtonData(
    var label: Component,
    var tooltip: Component?,
    var width: Int
)

fun CommonButtonData.toDhl(): net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.CommonButtonData {
    return net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.CommonButtonData(
        label = PaperAdventure.asVanilla(this.label),
        tooltip = PaperAdventure.asVanilla(this.tooltip),
        width = this.width,
    )
}