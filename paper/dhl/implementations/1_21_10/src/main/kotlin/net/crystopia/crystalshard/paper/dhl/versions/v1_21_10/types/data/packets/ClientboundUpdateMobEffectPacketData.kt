package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EffectInstance

data class ClientboundUpdateMobEffectPacketData(
    var entityId: Int,
    var effect: EffectInstance,
    var blend: Boolean
)
