package net.crystopia.crystalshard.dhl.shared.data.scoreboard

import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.NumberFormat
import net.minecraft.network.chat.Component

data class ScoreData<T : FormatData<*>>(
    var displayId: String,
    var ownerName: String,
    var score: Int,
    var displayName: Component,
    var numberFormat: NumberFormat,
    var format: T,
)
