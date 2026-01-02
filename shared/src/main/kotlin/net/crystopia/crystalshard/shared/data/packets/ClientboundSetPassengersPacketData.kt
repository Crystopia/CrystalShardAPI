package net.crystopia.crystalshard.shared.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundSetPassengersPacketData(
    var entity: Entity
)