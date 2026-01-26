package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.blocks.BlockPos

/**
 * Read more about Events: [Packets#World_Event](https://minecraft.wiki/w/Java_Edition_protocol/Packets#World_Event)
 */
data class ClientboundLevelEventPacketData(
    var type: Int,
    var pos:  BlockPos,
    var data: Int,
    var globalEvent: Boolean
)
