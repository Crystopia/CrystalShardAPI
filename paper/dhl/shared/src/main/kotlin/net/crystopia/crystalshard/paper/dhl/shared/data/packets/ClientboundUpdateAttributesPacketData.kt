package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.attributes.Attribute
import net.minecraft.core.Holder
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes
import java.util.UUID

data class ClientboundUpdateAttributesPacketData(
    var entityId: Int, var attributes: MutableList<Attribute>
)



