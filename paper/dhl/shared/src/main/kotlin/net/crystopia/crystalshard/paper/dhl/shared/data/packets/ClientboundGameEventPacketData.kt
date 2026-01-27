package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.game.GameEventType

data class ClientboundGameEventPacketData(
    var type : GameEventType,
    var action: Float
)
