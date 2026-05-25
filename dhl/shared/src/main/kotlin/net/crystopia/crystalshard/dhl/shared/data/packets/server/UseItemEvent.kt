package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.enums.server.InteractionHand

data class UseItemEvent(
    var hand: InteractionHand,
    var sequence: Int,
    var yRot: Float?,
    var xRot: Float?,
    var timestamp: Long
)
