package net.crystopia.crystalshard.paper.dhl.shared.data.packets.custom

data class EntityMetadata<T : Any>(
    var index: Int,
    var type: EntityDataSerializerType,
    var value: T
)