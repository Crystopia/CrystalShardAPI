package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.entities

import net.crystopia.crystalshard.dhl.shared.data.entities.EffectInstance
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.enums.entities.EffectType
import net.minecraft.world.effect.MobEffectInstance

internal fun EffectInstance.build(): MobEffectInstance {
    return MobEffectInstance(
        EffectType.convert(type).id, duration, amplifier, ambient, visible, showIcon
    )
}