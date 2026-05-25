package net.crystopia.crystalshard.paper.dhl.types.attributes

import org.bukkit.attribute.AttributeModifier

data class AttributeModifiers(
    var id: String,
    var amount: Double,
    var operation: AttributeModifier.Operation
)
