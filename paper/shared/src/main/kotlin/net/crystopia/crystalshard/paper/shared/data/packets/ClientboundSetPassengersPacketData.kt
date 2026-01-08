package net.crystopia.crystalshard.paper.shared.data.packets

import net.minecraft.world.entity.Entity

data class ClientboundSetPassengersPacketData(
    var entity: Entity
)