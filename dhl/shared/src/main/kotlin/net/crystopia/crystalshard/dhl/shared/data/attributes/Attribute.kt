package net.crystopia.crystalshard.dhl.shared.data.attributes

import net.minecraft.core.Holder

data class Attribute(
    /**
     * @see Attributes
     */
    var id: Holder<net.minecraft.world.entity.ai.attributes.Attribute>,
    var value: Double,
    var modifiers: MutableList<AttributeModifiers>
)
