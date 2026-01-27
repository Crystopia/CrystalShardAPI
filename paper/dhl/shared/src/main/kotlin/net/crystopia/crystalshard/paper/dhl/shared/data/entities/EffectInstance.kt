package net.crystopia.crystalshard.paper.dhl.shared.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EffectType
import net.minecraft.world.effect.MobEffectInstance

data class EffectInstance(
    var type: EffectType,
    var duration: Int,
    var amplifier: Int,
    var ambient: Boolean,
    var visible: Boolean,
    var showIcon: Boolean,
)