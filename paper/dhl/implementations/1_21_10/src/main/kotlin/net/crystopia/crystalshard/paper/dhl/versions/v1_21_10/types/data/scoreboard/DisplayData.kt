package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.scoreboard

import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.NumberFormat
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.ObjectiveCriteria
import net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard.RenderType
import net.kyori.adventure.text.Component

data class DisplayData<T : FormatData<*>>(
    var name: String,
    var displayName: Component,
    var displayAutoUpdate: Boolean,
    var numberFormat: NumberFormat,
    var format: FormatData<T>,
    var renderType: RenderType,
    var criteria: ObjectiveCriteria
)










