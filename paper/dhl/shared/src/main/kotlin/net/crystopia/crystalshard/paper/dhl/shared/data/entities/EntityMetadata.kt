package net.crystopia.crystalshard.paper.dhl.shared.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType

data class EntityMetadata<T>(
    var index: Int,
    var type: EntityDataSerializerType,
    var value: T
)