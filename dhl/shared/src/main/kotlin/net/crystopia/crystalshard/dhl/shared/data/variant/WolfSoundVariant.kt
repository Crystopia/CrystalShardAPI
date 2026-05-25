package net.crystopia.crystalshard.dhl.shared.data.variant

import net.minecraft.sounds.SoundEvent
import net.minecraft.world.level.block.SoundType

data class WolfSoundVariant(
    var ambientSound: SoundEvent,
    var deathSound: SoundEvent,
    var growlSound: SoundEvent,
    var hurtSound: SoundEvent,
    var pantSound: SoundEvent,
    var whineSound: SoundEvent
)
