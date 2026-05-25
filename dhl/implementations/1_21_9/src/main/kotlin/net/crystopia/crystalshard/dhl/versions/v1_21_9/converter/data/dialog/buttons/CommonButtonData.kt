package net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.dialog.buttons

import net.minecraft.server.dialog.CommonButtonData
import java.util.*

fun net.crystopia.crystalshard.dhl.shared.data.dialog.buttons.CommonButtonData.build(): CommonButtonData {
    var mcTooltip: net.minecraft.network.chat.Component? = null
    if (tooltip != null) mcTooltip = tooltip

    return CommonButtonData(
        label,
        Optional.ofNullable(mcTooltip),
        width
    )
}