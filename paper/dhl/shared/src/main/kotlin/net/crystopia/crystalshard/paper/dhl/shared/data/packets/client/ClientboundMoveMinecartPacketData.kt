package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.MinecartStep

data class ClientboundMoveMinecartPacketData(
    var entityId: Int,
    var lerpSteps: MutableList<MinecartStep>
)