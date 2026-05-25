package net.crystopia.crystalshard.dhl.shared.data.scoreboard

import net.minecraft.network.chat.Component

open class FormatData<T : Any>

data class FixedFormatData(
    var text: Component
) : FormatData<FixedFormatData>()

data class StyledFormatData(
    var style: Any
) : FormatData<StyledFormatData>()

class BlankFormatData : FormatData<BlankFormatData>()