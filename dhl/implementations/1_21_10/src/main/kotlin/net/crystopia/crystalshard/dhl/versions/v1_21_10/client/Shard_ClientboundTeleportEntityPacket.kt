package net.crystopia.crystalshard.dhl.versions.v1_21_10.client

import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.PositionMoveRotation
import net.minecraft.world.entity.Relative
import net.minecraft.world.phys.Vec3

class Shard_ClientboundTeleportEntityPacket : IPacket<ClientboundTeleportEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {

        return ClientboundTeleportEntityPacket(
            packetObj.entity.id,
            PositionMoveRotation(
                Vec3(
                    packetObj.location.x,
                    packetObj.location.y,
                    packetObj.location.z
                ),
                Vec3.ZERO,
                packetObj.location.yaw,
                packetObj.location.pitch
            ),
            mutableSetOf<Relative?>(),
            packetObj.onGround

        )
    }
}