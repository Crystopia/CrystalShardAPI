package net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.data.particles

import net.minecraft.core.particles.*
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.EntityPositionSource
import org.joml.Vector3f

fun net.crystopia.crystalshard.dhl.shared.data.particles.BlockParticleOption.build(): BlockParticleOption {
    return BlockParticleOption(
        net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<BlockParticleOption>,
        block.defaultBlockState()
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.ColorParticleOption.build(): ColorParticleOption {
    return ColorParticleOption.create(
        net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<ColorParticleOption>,
        color
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.DustColorTransitionOptions.build(): DustColorTransitionOptions {
    return DustColorTransitionOptions(
        numberToVector3f(fromColor), numberToVector3f(toColor), scale
    )
}


fun net.crystopia.crystalshard.dhl.shared.data.particles.ItemParticleOption.build(): ItemParticleOption {
    return ItemParticleOption(
        net.crystopia.crystalshard.dhl.versions.v1_21_1.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<ItemParticleOption>,
        item
    )
}

fun net.crystopia.crystalshard.dhl.shared.data.particles.DustParticleOptions.build(): DustParticleOptions {
    return DustParticleOptions(
        numberToVector3f(color), scale
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

private fun numberToVector3f(color: Int): Vector3f {
    // || || RRGGBB
    // 0x FF FFFFFF
    val numberStr = color.toString()
    // val a = numberStr.substring(0,3).toDouble()
    val r = numberStr.substring(2, 4).toFloat()
    val g = numberStr.substring(3, 5).toFloat()
    val b = numberStr.substring(4, 6).toFloat()

    return Vector3f(r, g, b)
}