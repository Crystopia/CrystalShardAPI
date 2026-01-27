package net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EffectInstance
import net.minecraft.world.effect.MobEffectInstance

internal fun EffectInstance.build(): MobEffectInstance {
    return MobEffectInstance(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_1.converter.enums.entities.EffectType.convert(type).id, duration, amplifier, ambient, visible, showIcon
    )
}