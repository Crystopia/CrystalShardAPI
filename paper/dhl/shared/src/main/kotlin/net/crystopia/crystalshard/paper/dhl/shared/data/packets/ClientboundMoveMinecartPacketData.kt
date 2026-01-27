package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.MinecartStep
import net.minecraft.world.entity.vehicle.NewMinecartBehavior

data class ClientboundMoveMinecartPacketData(
    var entityId: Int,
    var lerpSteps: MutableList<MinecartStep>
)