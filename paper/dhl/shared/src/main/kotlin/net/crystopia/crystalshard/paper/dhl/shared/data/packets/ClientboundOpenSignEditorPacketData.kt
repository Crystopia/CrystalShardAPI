package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom.BlockPos

data class ClientboundOpenSignEditorPacketData(
    var blockPos: BlockPos,
    var isFrontText: Boolean
)
