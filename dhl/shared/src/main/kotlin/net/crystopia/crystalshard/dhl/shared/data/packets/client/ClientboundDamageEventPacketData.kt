package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.Entity

data class ClientboundDamageEventPacketData(
    var entity: Entity,
    var damageSource: DamageSource
)
