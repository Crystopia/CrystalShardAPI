package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.packets.relativesSet
import net.crystopia.crystalshard.dhl.versions.v1_21_9.converter.data.world.build
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.world.entity.PositionMoveRotation

class Shard_ClientboundPlayerPositionPacket : IClientPacket<ClientboundPlayerPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerPositionPacketData
    ): ClientboundPlayerPositionPacket {
        return ClientboundPlayerPositionPacket(
            packetObj.teleportId,
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