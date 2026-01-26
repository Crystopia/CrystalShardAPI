package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.attributes

import net.minecraft.world.entity.ai.attributes.AttributeModifier

data class AttributeModifiers(
    var id: String,
    var amount: Double,
    var operation: AttributeModifier.Operation
)
