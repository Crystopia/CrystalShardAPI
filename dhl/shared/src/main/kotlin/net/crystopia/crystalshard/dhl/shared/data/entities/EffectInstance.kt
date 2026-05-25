package net.crystopia.crystalshard.dhl.shared.data.entities

import net.crystopia.crystalshard.dhl.shared.enums.entities.EffectType

data class EffectInstance(
    var type: EffectType,
    var duration: Int,
    var amplifier: Int,
    var ambient: Boolean,
    var visible: Boolean,
    var showIcon: Boolean,
)