package net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.packets

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.dhl.versions.v1_21_11.converter.data.world.build
import net.minecraft.world.entity.vehicle.minecart.NewMinecartBehavior

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