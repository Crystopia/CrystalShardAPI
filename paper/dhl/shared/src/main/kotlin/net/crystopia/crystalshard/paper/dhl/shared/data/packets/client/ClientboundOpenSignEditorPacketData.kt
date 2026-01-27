package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos

data class ClientboundOpenSignEditorPacketData(
    var blockPos: BlockPos,
    var isFrontText: Boolean
)
