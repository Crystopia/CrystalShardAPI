package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.InteractionHand

data class UseItemEvent(
    var hand: InteractionHand,
    var sequence: Int,
    var yRot: Float?,
    var xRot: Float?,
    var timestamp: Long
)
