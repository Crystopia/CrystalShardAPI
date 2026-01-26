package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.game.GameEventType

data class ClientboundGameEventPacketData(
    var type : GameEventType,
    var action: Float
)
