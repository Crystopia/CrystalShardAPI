package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.attributes.Attribute
import net.minecraft.world.entity.Entity

data class ClientboundUpdateAttributesPacketData(
    var entity: Entity, var attributes: MutableList<Attribute>
)



