package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.craftbukkit.damage.CraftDamageSourceBuilder
import org.bukkit.damage.DamageSource

data class ClientboundDamageEventPacketData(
    var entity: org.bukkit.entity.Entity,
    var damageSource: DamageSource
)
