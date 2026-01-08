package net.crystopia.crystalshard.paper.versions.v1_21_10.packets

import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundTeleportEntityPacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket
import net.minecraft.world.entity.PositionMoveRotation
import net.minecraft.world.entity.Relative
import net.minecraft.world.phys.Vec3

class Shard_ClientboundTeleportEntityPacket : IPacket<ClientboundTeleportEntityPacketData> {

    override fun createPacket(
        packetObj: ClientboundTeleportEntityPacketData
    ): ClientboundTeleportEntityPacket {

        return ClientboundTeleportEntityPacket(
            packetObj.entityId,
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
            false

        )
    }
}