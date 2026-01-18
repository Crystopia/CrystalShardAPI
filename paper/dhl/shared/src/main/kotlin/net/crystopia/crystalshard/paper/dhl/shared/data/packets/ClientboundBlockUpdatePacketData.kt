package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockType

data class ClientboundBlockUpdatePacketData(
    var blockPos: BlockPos, var state: BlockType
)
