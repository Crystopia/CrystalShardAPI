package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.scoreboard

import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat
import net.kyori.adventure.text.Component

data class ScoreData<T : FormatData<*>>(
    var displayId : String,
    var ownerName: String,
    var score: Int,
    var displayName: Component,
    var numberFormat: NumberFormat,
    var format: FormatData<T>,
)
