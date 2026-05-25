package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.custom.Location
import net.minecraft.world.entity.EntityType
import java.util.*

data class ClientboundAddEntityPacketData(
    var entityId: Int,
    var entityUUID: UUID,
    var location: Location,
    var entityType: EntityType<*>,
    var data: Int,
    var yHeadRot: Double = 0.0
)