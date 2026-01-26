package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EffectType
import net.minecraft.world.effect.MobEffectInstance

data class EffectInstance(
    var type: EffectType,
    var duration: Int,
    var amplifier: Int,
    var ambient: Boolean,
    var visible: Boolean,
    var showIcon: Boolean,
) {
    fun build(): MobEffectInstance {
        return MobEffectInstance(
            type.id, duration, amplifier, ambient, visible, showIcon
        )
    }
}
