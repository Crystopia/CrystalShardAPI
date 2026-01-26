package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import org.bukkit.block.BlockType

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var state: BlockType
)
