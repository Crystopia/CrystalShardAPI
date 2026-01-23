package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EffectType

data class ClientboundRemoveMobEffectPacketData(
    var entityId: Int,
    var effect: EffectType
)
