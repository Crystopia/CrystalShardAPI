package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EffectInstance

data class ClientboundUpdateMobEffectPacketData(
    var entityId: Int,
    var effect: EffectInstance,
    var blend: Boolean
)
