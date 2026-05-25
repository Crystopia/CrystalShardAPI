package net.crystopia.crystalshard.paper.dhl.types.attributes

data class Attribute(
    var id: org.bukkit.attribute.Attribute,
    var value: Double,
    var modifiers: MutableList<AttributeModifiers>
)
