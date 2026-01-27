package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.attributes.Attribute

data class ClientboundUpdateAttributesPacketData(
    var entityId: Int, var attributes: MutableList<Attribute>
)



