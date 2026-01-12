package net.crystopia.crystalshard.paper.shared.data.packets

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.block.entity.BlockEntityType

data class ClientboundBlockEntityDataPacketData(
    var blockPos: BlockPos,
    var type: BlockEntityType<*>,
    var nbt: CompoundTag
)
