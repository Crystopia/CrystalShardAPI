package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.enums.blocks.BlockType

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var state: BlockType
)
