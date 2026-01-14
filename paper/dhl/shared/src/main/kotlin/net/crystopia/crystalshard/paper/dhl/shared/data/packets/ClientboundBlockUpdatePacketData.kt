package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.state.BlockState

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var state: BlockState
)
