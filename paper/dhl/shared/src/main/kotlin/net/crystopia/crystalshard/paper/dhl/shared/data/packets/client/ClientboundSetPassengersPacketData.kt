package net.crystopia.crystalshard.paper.dhl.shared.data.packetsid

import org.bukkit.entity.Entity

data class ClientboundSetPassengersPacketData(
    var entity: Entity, var passengers : MutableList<Entity>
)