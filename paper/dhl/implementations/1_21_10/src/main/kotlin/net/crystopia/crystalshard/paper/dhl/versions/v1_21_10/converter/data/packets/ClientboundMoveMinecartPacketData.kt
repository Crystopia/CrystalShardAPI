package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.world.entity.vehicle.NewMinecartBehavior
import kotlin.collections.forEach

fun ClientboundMoveMinecartPacketData.lerpSteps(): MutableList<NewMinecartBehavior.MinecartStep> {
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