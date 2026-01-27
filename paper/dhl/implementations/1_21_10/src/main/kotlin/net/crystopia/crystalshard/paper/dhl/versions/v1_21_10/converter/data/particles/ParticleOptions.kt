package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.particles

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.particles.ParticleOptions
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
import org.bukkit.craftbukkit.block.data.CraftBlockData
import org.bukkit.craftbukkit.inventory.CraftItemStack
import org.bukkit.inventory.ItemStack

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.BlockParticleOption.build(): BlockParticleOption {
    return BlockParticleOption(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<BlockParticleOption>,
        (block.createBlockData() as CraftBlockData).state
    )
}


fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.ColorParticleOption.build(): ColorParticleOption {
    return ColorParticleOption.create(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<ColorParticleOption>, color
    )
}


fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustColorTransitionOptions.build(): DustColorTransitionOptions {
    return DustColorTransitionOptions(
        fromColor, toColor, scale
    )
}


fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.ItemParticleOption.build(): ItemParticleOption {
    return ItemParticleOption(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type) as net.minecraft.core.particles.ParticleType<ItemParticleOption>, CraftItemStack.asNMSCopy(item)
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.DustParticleOptions.build(): DustParticleOptions {
    return DustParticleOptions(
        color, scale
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.PowerParticleOption.build(): PowerParticleOption {
    return PowerParticleOption.create(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<PowerParticleOption>, power
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.SculkChargeParticleOptions.build(): SculkChargeParticleOptions {
    return SculkChargeParticleOptions(
        roll
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.ShriekParticleOption.build(): ShriekParticleOption {
    return ShriekParticleOption(
        delay
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.SpellParticleOption.build(): SpellParticleOption {
    return SpellParticleOption.create(
        net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.particles.ParticleType.convert(type).id as net.minecraft.core.particles.ParticleType<SpellParticleOption>, color, power
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.TrailParticleOption.build(): TrailParticleOption {
    return TrailParticleOption(
        Vec3(x, y, z), color, duration
    )
}

fun net.crystopia.crystalshard.paper.dhl.shared.data.particles.VibrationParticleOption.build(): VibrationParticleOption {

    if (entityId != null) {
        requireNotNull(entityId)
        requireNotNull(offSet)

        val fakeEntity = Display.ItemDisplay(
            EntityType.ITEM_DISPLAY, (Bukkit.getWorld("world") as CraftWorld).handle
        )
        fakeEntity.id = entityId!!

        return VibrationParticleOption(
            EntityPositionSource(
                fakeEntity, offSet!!
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