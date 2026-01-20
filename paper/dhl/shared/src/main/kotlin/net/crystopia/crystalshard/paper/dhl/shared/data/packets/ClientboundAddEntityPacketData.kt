package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityType
import org.bukkit.Location
import java.util.*

data class ClientboundAddEntityPacketData(
    var entityId: Int,
    var entityUUID: UUID,
    var location: Location,
    var entityType: EntityType,
    var data: Int,
    var yHeadRot: Double = 0.0
)