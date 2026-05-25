package net.crystopia.crystalshard.dhl.shared.data.scoreboard

import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.NumberFormat
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.ObjectiveCriteria
import net.crystopia.crystalshard.dhl.shared.enums.scoreboard.RenderType
import net.minecraft.network.chat.Component

data class DisplayData<T : FormatData<*>>(
    var name: String,
    var displayName: Component,
    var displayAutoUpdate: Boolean,
    var numberFormat: NumberFormat,
    var format: T,
    var renderType: RenderType,
    var criteria: ObjectiveCriteria
)










