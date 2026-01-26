package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.entities.MinecartStep
import net.minecraft.world.entity.vehicle.NewMinecartBehavior

data class ClientboundMoveMinecartPacketData(
    var entityId: Int,
    var lerpSteps: MutableList<MinecartStep>
) {
    fun lerpSteps(): MutableList<NewMinecartBehavior.MinecartStep> {
        val list = mutableListOf<NewMinecartBehavior.MinecartStep>()
        lerpSteps.forEach { (position, movement, yRot, xRot, weight) ->
            list.add(
                NewMinecartBehavior.MinecartStep(
                    position.build(), movement.build(), yRot, xRot, weight
                )
            )
        }
        return list
    }
}