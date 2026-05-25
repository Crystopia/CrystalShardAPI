package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.enums.server.InteractionHand

data class UseItemOnEvent(
    var blockHit: BlockHitResult,
    var hand: InteractionHand,
    var sequence: Int,
    var timestamp: Long
)
