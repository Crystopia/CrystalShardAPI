package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockEntityType
import net.minecraft.nbt.CompoundTag

data class ClientboundBlockEntityDataPacketData(
    var blockPos: BlockPos,
    var type: BlockEntityType,
    var nbt: CompoundTag
)
