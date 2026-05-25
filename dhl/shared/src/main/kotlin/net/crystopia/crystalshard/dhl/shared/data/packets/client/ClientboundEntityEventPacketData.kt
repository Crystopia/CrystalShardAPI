package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level

data class ClientboundEntityEventPacketData(
    var entity: Entity,
    var status: Byte,
    var world: Level
)