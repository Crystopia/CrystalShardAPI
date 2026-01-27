package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*

data class ClientboundAddEntityPacketData(
    var entityId: Int,
    var entityUUID: UUID,
    var location: Location,
    var entityType: EntityType,
    var data: Int,
    var yHeadRot: Double = 0.0
)