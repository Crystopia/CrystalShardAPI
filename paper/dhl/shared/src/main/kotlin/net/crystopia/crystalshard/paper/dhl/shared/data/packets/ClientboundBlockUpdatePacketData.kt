package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var block: org.bukkit.block.BlockType
)
