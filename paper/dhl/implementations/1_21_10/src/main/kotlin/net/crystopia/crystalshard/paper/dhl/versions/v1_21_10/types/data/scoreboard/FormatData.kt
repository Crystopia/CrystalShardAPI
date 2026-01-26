package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.scoreboard

import net.kyori.adventure.text.Component

open class FormatData<T : Any>

data class FixedFormatData(
    var text: Component
) : FormatData<FixedFormatData>()

data class StyledFormatData(
    var style: Any
) : FormatData<StyledFormatData>()

class BlankFormatData : FormatData<BlankFormatData>()