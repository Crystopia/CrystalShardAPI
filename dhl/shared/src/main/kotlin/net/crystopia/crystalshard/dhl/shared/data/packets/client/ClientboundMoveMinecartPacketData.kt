package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.entities.MinecartStep
import net.minecraft.world.entity.Entity

data class ClientboundMoveMinecartPacketData(
    var entity: Entity,
    var lerpSteps: MutableList<MinecartStep>
)