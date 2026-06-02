package net.crystopia.crystalshard.paper.dhl.types.attributes

data class Attribute(
    var id: Any,
    var value: Double,
    var modifiers: MutableList<AttributeModifiers>
)
