package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.entities.MinecartStep
import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundMoveMinecartPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundMoveMinecartPacket
import net.minecraft.world.entity.vehicle.NewMinecartBehavior

class Shard_ClientboundMoveMinecartPacket : IPacket<ClientboundMoveMinecartPacketData> {

    override fun createPacket(
        packetObj: ClientboundMoveMinecartPacketData
    ): ClientboundMoveMinecartPacket {
        return ClientboundMoveMinecartPacket(
            packetObj.entityId,
            lerpStepsBuilder(packetObj.lerpSteps)
        )
    }

    fun lerpStepsBuilder(lerpSteps:MutableList<MinecartStep>): MutableList<NewMinecartBehavior.MinecartStep> {
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