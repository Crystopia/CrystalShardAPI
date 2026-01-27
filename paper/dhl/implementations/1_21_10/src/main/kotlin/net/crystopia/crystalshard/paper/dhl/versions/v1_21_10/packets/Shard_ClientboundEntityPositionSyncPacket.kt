package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundEntityPositionSyncPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket
import net.minecraft.world.entity.PositionMoveRotation

class Shard_ClientboundEntityPositionSyncPacket : IPacket<ClientboundEntityPositionSyncPacketData> {

    override fun createPacket(
        packetObj: ClientboundEntityPositionSyncPacketData
    ): ClientboundEntityPositionSyncPacket {
        return ClientboundEntityPositionSyncPacket(
            packetObj.entityId,
            PositionMoveRotation(
                packetObj.values.position.build(),
                packetObj.values.deltaMovement.build(),
                packetObj.values.yRot,
                packetObj.values.xRot
            ),
            packetObj.onGround
        )
    }
}