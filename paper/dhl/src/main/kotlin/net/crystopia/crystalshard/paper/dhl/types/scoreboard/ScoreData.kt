package net.crystopia.crystalshard.paper.dhl.types.scoreboard

import net.kyori.adventure.text.Component

data class ScoreData<T : FormatData<*>>(
    var displayId: String,
    var ownerName: String,
    var score: Int,
    var displayName: Component,
    var numberFormat: net.crystopia.crystalshard.dhl.shared.enums.scoreboard.NumberFormat,
    var format: T,
)
