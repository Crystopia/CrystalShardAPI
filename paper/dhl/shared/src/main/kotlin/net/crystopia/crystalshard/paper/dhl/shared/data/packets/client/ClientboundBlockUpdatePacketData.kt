package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos
import org.bukkit.block.BlockType

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var block: BlockType
)
