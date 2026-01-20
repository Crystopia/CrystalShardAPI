package net.crystopia.crystalshard.paper.dhl.shared.data.attributes

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.Attributes

data class Attribute(
    /**
     * @see Attributes
     */
    var id: Holder<net.minecraft.world.entity.ai.attributes.Attribute>,
    var value: Double,
    var modifiers: MutableList<AttributeModifiers>
)
