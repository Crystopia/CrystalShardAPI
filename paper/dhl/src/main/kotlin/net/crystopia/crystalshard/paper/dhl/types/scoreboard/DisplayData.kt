package net.crystopia.crystalshard.paper.dhl.types.scoreboard

import net.kyori.adventure.text.Component

data class DisplayData<T : FormatData<*>>(
    var name: String,
    var displayName: Component,
    var displayAutoUpdate: Boolean,
    var numberFormat: net.crystopia.crystalshard.dhl.shared.enums.scoreboard.NumberFormat,
    var format: T,
    var renderType: net.crystopia.crystalshard.dhl.shared.enums.scoreboard.RenderType,
    var criteria: net.crystopia.crystalshard.dhl.shared.enums.scoreboard.ObjectiveCriteria
)










