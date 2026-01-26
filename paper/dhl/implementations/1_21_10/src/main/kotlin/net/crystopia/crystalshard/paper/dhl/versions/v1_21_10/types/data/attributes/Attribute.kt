package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.attributes

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
