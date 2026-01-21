package net.crystopia.crystalshard.paper.dhl.shared.data.particles

import com.mojang.datafixers.util.Either
import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockType
import net.crystopia.crystalshard.paper.dhl.shared.enums.particles.ParticleType
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.DustColorTransitionOptions
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.PowerParticleOption
import net.minecraft.core.particles.ScalableParticleOptionsBase
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
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.entity.ItemDisplay
import org.bukkit.inventory.ItemStack
import java.util.UUID

class ParticleOptions

data class BlockParticleOption(
    var type: ParticleType, var block: BlockType
) {
    fun build(): BlockParticleOption {
        return BlockParticleOption(
            type.id as net.minecraft.core.particles.ParticleType<BlockParticleOption>, block.block.defaultBlockState()
        )
    }
}

data class ColorParticleOption(
    var type: ParticleType, var color: Int
) {
    fun build(): net.minecraft.core.particles.ColorParticleOption {
        return ColorParticleOption.create(
            type.id as net.minecraft.core.particles.ParticleType<ColorParticleOption>, color
        )
    }
}

data class DustColorTransitionOptions(
    var fromColor: Int, var toColor: Int, var scale: Float
) {
    fun build(): net.minecraft.core.particles.DustColorTransitionOptions {
        return DustColorTransitionOptions(
            fromColor, toColor, scale
        )
    }
}

data class ItemParticleOption(
    var type: ParticleType, var item: ItemStack
) {
    fun build(): net.minecraft.core.particles.ItemParticleOption {
        return ItemParticleOption(
            type.id as net.minecraft.core.particles.ParticleType<ItemParticleOption>, CraftItemStack.asNMSCopy(item)
        )
    }
}

data class DustParticleOptions(
    var color: Int, var scale: Float
) {
    fun build(): net.minecraft.core.particles.DustParticleOptions {
        return DustParticleOptions(
            color, scale
        )
    }
}

data class PowerParticleOption(
    var type: ParticleType, var power: Float
) {
    fun build(): net.minecraft.core.particles.PowerParticleOption {
        return PowerParticleOption.create(
            type.id as net.minecraft.core.particles.ParticleType<PowerParticleOption>, power
        )
    }
}

data class SculkChargeParticleOptions(
    var roll: Float,
) {
    fun build(): net.minecraft.core.particles.SculkChargeParticleOptions {
        return SculkChargeParticleOptions(
            roll
        )
    }
}

data class ShriekParticleOption(
    var delay: Int
) {
    fun build(): net.minecraft.core.particles.ShriekParticleOption {
        return ShriekParticleOption(
            delay
        )
    }
}

data class SpellParticleOption(
    var type: ParticleType,
    var color: Int,
    var power: Float,
) {
    fun build(): net.minecraft.core.particles.SpellParticleOption {
        return SpellParticleOption.create(
            type.id as net.minecraft.core.particles.ParticleType<SpellParticleOption>, color, power
        )
    }
}

data class TrailParticleOption(
    var x: Double, var y: Double, var z: Double, var color: Int, var duration: Int
) {
    fun build(): TrailParticleOption {
        return net.minecraft.core.particles.TrailParticleOption(
            Vec3(x, y, z), color, duration
        )
    }
}

data class VibrationParticleOption(
    var entityId: Int?, var offSet: Float?,

    var blockPos: BlockPos?,

    var arrivalInTicks: Int
) {
    fun build(): VibrationParticleOption {

        if (entityId != null) {
            requireNotNull(entityId)
            requireNotNull(offSet)

            val fakeEntity = Display.ItemDisplay(
                EntityType.ITEM_DISPLAY, (Bukkit.getWorld("world") as CraftWorld).handle
            )
            fakeEntity.id = entityId!!

            return net.minecraft.core.particles.VibrationParticleOption(
                EntityPositionSource(
                    fakeEntity, offSet!!
                ), arrivalInTicks
            )
        } else if (blockPos != null) {
            requireNotNull(blockPos)

            return net.minecraft.core.particles.VibrationParticleOption(
                BlockPositionSource(
                    net.minecraft.core.BlockPos(
                        blockPos!!.x, blockPos!!.y, blockPos!!.z
                    )
                ), arrivalInTicks
            )
        }
    }
}