package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.enums.game.GameEventType

data class ClientboundGameEventPacketData(
    var type: GameEventType,
    var action: Float
)
