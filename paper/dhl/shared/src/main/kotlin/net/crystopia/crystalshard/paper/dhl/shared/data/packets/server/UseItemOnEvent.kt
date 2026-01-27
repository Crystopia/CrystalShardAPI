package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.InteractionHand

data class UseItemOnEvent(
    var blockHit: BlockHitResult,
    var hand: InteractionHand,
    var sequence: Int,
    var timestamp: Long
)
