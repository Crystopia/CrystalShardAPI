package net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.particles

import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.particles.ParticleType
import net.minecraft.core.particles.*
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.EntityPositionSource
import net.minecraft.world.phys.Vec3

fun net.crystopia.crystalshard.dhl.shared.data.particles.BlockParticleOption.build(): BlockParticleOption {
    return BlockParticleOption(
        net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<BlockParticleOption>,
        block.defaultBlockState()
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.ColorParticleOption.build(): ColorParticleOption {
    return ColorParticleOption.create(
        net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<ColorParticleOption>,
        color
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.DustColorTransitionOptions.build(): DustColorTransitionOptions {
    return DustColorTransitionOptions(
        fromColor, toColor, scale
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.ItemParticleOption.build(): ItemParticleOption {
    return ItemParticleOption(
        net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<ItemParticleOption>,
        item
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.DustParticleOptions.build(): DustParticleOptions {
    return DustParticleOptions(
        color, scale
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.PowerParticleOption.build(): PowerParticleOption {
    return PowerParticleOption.create(
        net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<PowerParticleOption>,
        power
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.SculkChargeParticleOptions.build(): SculkChargeParticleOptions {
    return SculkChargeParticleOptions(
        roll
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.ShriekParticleOption.build(): ShriekParticleOption {
    return ShriekParticleOption(
        delay
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.SpellParticleOption.build(): SpellParticleOption {
    return SpellParticleOption.create(
        ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<SpellParticleOption>,
        color,
        power
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.TrailParticleOption.build(): TrailParticleOption {
    return TrailParticleOption(
        Vec3(x, y, z), color, duration
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.VibrationParticleOption.build(): VibrationParticleOption {
    if (entity != null) {
        requireNotNull(entity)
        requireNotNull(offSet)

        return VibrationParticleOption(
            EntityPositionSource(
                entity!!, offSet!!
            ), arrivalInTicks
        )
    } else if (blockPos != null) {
        requireNotNull(blockPos)
        return VibrationParticleOption(
            BlockPositionSource(
                net.minecraft.core.BlockPos(
                    blockPos!!.x, blockPos!!.y, blockPos!!.z
                )
            ), arrivalInTicks
        )
    }
    throw NoSuchMethodException("There are only entity or block methods.")
}