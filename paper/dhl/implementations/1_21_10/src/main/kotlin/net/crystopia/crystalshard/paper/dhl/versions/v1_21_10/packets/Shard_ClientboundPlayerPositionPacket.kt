package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.packets.relativesSet
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.world.entity.PositionMoveRotation

class Shard_ClientboundPlayerPositionPacket : IPacket<ClientboundPlayerPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerPositionPacketData
    ): ClientboundPlayerPositionPacket {
        return ClientboundPlayerPositionPacket(
            packetObj.entityId,
            PositionMoveRotation(
                packetObj.change.position.build(),
                packetObj.change.deltaMovement.build(),
                packetObj.change.yRot,
                packetObj.change.xRot
            ),
            packetObj.relativesSet()
        )
    }
}