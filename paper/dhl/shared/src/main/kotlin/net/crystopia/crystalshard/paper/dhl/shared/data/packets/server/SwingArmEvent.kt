package net.crystopia.crystalshard.paper.dhl.shared.data.packets.server

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.InteractionHand

data class SwingArmEvent(
    var hand: InteractionHand
)