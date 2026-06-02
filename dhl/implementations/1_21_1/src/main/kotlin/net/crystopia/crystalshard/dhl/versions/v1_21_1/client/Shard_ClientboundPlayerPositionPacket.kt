package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerPositionPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket
import net.minecraft.world.entity.RelativeMovement

class Shard_ClientboundPlayerPositionPacket : IClientPacket<ClientboundPlayerPositionPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerPositionPacketData
    ): ClientboundPlayerPositionPacket {
        val set = packetObj.relatives.map { position ->
            RelativeMovement.valueOf(position.name)
        }

        return ClientboundPlayerPositionPacket(
            packetObj.change.position.x,
            packetObj.change.position.y,
            packetObj.change.position.z,
            packetObj.change.xRot,
            packetObj.change.yRot,
            set.toMutableSet(),
            packetObj.teleportId,
        )
    }
}