package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundEntityPositionSyncPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundEntityPositionSyncPacket
import net.minecraft.world.entity.PositionMoveRotation

class Shard_ClientboundEntityPositionSyncPacket : IPacket<ClientboundEntityPositionSyncPacketData> {

    override fun createPacket(
        packetObj: ClientboundEntityPositionSyncPacketData
    ): ClientboundEntityPositionSyncPacket {
        return ClientboundEntityPositionSyncPacket(
            packetObj.entity.id,
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