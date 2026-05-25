package net.crystopia.crystalshard.paper.dhl.types.particles

import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.dhl.shared.enums.particles.ParticleType
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.DustColorTransitionOptions
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.PowerParticleOption
import net.minecraft.core.particles.SculkChargeParticleOptions
import net.minecraft.core.particles.ShriekParticleOption
import net.minecraft.core.particles.SpellParticleOption
import net.minecraft.core.particles.TrailParticleOption
import net.minecraft.core.particles.VibrationParticleOption
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

open class ParticleOptions<T : Any, R : Any>

data class BlockParticleOption(
    var type: ParticleType, var block: Material
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.BlockParticleOption, BlockParticleOption>()

data class ColorParticleOption(
    var type: ParticleType, var color: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.ColorParticleOption, ColorParticleOption>()

data class DustColorTransitionOptions(
    var fromColor: Int, var toColor: Int, var scale: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.DustColorTransitionOptions, DustColorTransitionOptions>()

data class ItemParticleOption(
    var type: ParticleType, var item: ItemStack
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.ItemParticleOption, ItemParticleOption>()

data class DustParticleOptions(
    var color: Int, var scale: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.DustParticleOptions, DustParticleOptions>()

data class PowerParticleOption(
    var type: ParticleType, var power: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.PowerParticleOption, PowerParticleOption>()

data class SculkChargeParticleOptions(
    var roll: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.SculkChargeParticleOptions, SculkChargeParticleOptions>()

data class ShriekParticleOption(
    var delay: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.ShriekParticleOption, ShriekParticleOption>()

data class SpellParticleOption(
    var type: ParticleType,
    var color: Int,
    var power: Float,
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.SpellParticleOption, SpellParticleOption>()

data class TrailParticleOption(
    var x: Double, var y: Double, var z: Double, var color: Int, var duration: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.TrailParticleOption, TrailParticleOption>()

data class VibrationParticleOption(
    var entity: Entity?, var offSet: Float?,

    var blockPos: BlockPos?,

    var arrivalInTicks: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.types.particles.VibrationParticleOption, VibrationParticleOption>()