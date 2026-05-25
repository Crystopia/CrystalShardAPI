package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.entities.MinecartStep

data class ClientboundMoveMinecartPacketData(
    var entityId: Int,
    var lerpSteps: MutableList<MinecartStep>
)