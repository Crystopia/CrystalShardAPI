package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.minecraft.world.entity.Entity


data class ClientboundSetPassengersPacketData(
    var entity: Entity, var passengers : MutableList<Entity>
)