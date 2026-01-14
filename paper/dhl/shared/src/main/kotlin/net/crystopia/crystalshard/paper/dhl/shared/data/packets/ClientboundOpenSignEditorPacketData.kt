package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.core.BlockPos

data class ClientboundOpenSignEditorPacketData(
    var blockPos: BlockPos,
    var isFrontText: Boolean
)
