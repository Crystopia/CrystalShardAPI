package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import java.util.UUID

data class ClientboundUpdateAttributesPacketData(
    var entityId: Int, var attributes: MutableList<Attribute>
)

data class Attribute(
    /**
     * @see Attributes
     */
    var id: Holder<net.minecraft.world.entity.ai.attributes.Attribute>,
    var value: Double,
    var modifiers: MutableList<AttributeModifiers>
)

data class AttributeModifiers(
    var id: String,
    var amount: Double,
    var operation: AttributeModifier.Operation
)