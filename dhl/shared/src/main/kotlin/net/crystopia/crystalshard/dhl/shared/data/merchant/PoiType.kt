package net.crystopia.crystalshard.dhl.shared.data.merchant

import net.minecraft.world.level.block.state.BlockState

data class PoiType(
    var matchingStates: MutableSet<BlockState>,
    var maxTickets: Int,
    var validRange: Int
)
