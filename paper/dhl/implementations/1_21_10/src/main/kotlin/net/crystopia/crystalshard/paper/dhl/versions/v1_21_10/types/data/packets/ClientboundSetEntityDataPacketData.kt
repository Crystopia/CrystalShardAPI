package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.EntityMetadata

data class ClientboundSetEntityDataPacketData(
    var entityId: Int,
    var entityData: MutableList<EntityMetadata<*>>
)