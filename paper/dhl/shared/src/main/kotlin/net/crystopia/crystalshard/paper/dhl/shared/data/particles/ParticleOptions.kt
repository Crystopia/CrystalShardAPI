package net.crystopia.crystalshard.paper.dhl.shared.data.particles

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.enums.particles.ParticleType
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
import net.minecraft.world.entity.Display
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.gameevent.BlockPositionSource
import net.minecraft.world.level.gameevent.EntityPositionSource
import net.minecraft.world.phys.Vec3
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.CraftWorld
import org.bukkit.craftbukkit.block.CraftBlock
import org.bukkit.craftbukkit.block.CraftBlockType
import org.bukkit.craftbukkit.block.data.CraftBlockData
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

open class ParticleOptions<T : Any, R : Any>

data class BlockParticleOption(
    var type: ParticleType, var block: org.bukkit.block.BlockType
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.BlockParticleOption, BlockParticleOption>()

data class ColorParticleOption(
    var type: ParticleType, var color: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.ColorParticleOption, ColorParticleOption>()

data class DustColorTransitionOptions(
    var fromColor: Int, var toColor: Int, var scale: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustColorTransitionOptions, DustColorTransitionOptions>()

data class ItemParticleOption(
    var type: ParticleType, var item: ItemStack
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.ItemParticleOption, ItemParticleOption>()

data class DustParticleOptions(
    var color: Int, var scale: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustParticleOptions, DustParticleOptions>()

data class PowerParticleOption(
    var type: ParticleType, var power: Float
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.PowerParticleOption, PowerParticleOption>()

data class SculkChargeParticleOptions(
    var roll: Float,
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.SculkChargeParticleOptions, SculkChargeParticleOptions>()

data class ShriekParticleOption(
    var delay: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.ShriekParticleOption, ShriekParticleOption>()

data class SpellParticleOption(
    var type: ParticleType,
    var color: Int,
    var power: Float,
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.SpellParticleOption, SpellParticleOption>()

data class TrailParticleOption(
    var x: Double, var y: Double, var z: Double, var color: Int, var duration: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.TrailParticleOption, TrailParticleOption>()

data class VibrationParticleOption(
    var entityId: Int?, var offSet: Float?,

    var blockPos: BlockPos?,

    var arrivalInTicks: Int
) : ParticleOptions<net.crystopia.crystalshard.paper.dhl.shared.data.particles.VibrationParticleOption, VibrationParticleOption>()