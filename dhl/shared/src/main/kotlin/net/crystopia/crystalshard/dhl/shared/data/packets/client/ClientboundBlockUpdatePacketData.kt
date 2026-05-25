package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.blocks.BlockPos
import net.minecraft.world.level.block.Block

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var block: Block
)
