package net.crystopia.crystalshard.paper.shared.data.packets

import net.minecraft.core.BlockPos

data class ClientboundOpenSignEditorPacketData(
    var blockPos: BlockPos,
    var isFrontText: Boolean
)
