package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.entities

import net.crystopia.crystalshard.paper.dhl.shared.enums.entities.EntityDataSerializerType

data class EntityMetadata<T : Any>(
    var index: Int,
    var type: EntityDataSerializerType,
    var value: T
)