package net.crystopia.crystalshard.paper.dhl.versions.v1_21_9.converter.data.dialog.buttons

import io.papermc.paper.adventure.PaperAdventure
import net.minecraft.server.dialog.CommonButtonData
import java.util.Optional

fun net.crystopia.crystalshard.paper.dhl.shared.data.dialog.buttons.CommonButtonData.build(): CommonButtonData {
    var mcTooltip: net.minecraft.network.chat.Component? = null
    if (tooltip != null) mcTooltip = PaperAdventure.asVanilla(tooltip)

    return CommonButtonData(
        PaperAdventure.asVanilla(label),
        Optional.ofNullable(mcTooltip),
        width
    )
}