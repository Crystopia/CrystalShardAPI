package net.crystopia.crystalshard.dhl.shared.data.packets.server

import net.crystopia.crystalshard.dhl.shared.enums.server.InteractionHand

data class SwingArmEvent(
    var hand: InteractionHand
)