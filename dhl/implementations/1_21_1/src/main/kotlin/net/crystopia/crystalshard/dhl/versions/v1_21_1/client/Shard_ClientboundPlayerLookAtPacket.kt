package net.crystopia.crystalshard.dhl.versions.v1_21_1.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket

class Shard_ClientboundPlayerLookAtPacket : IClientPacket<ClientboundPlayerLookAtPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerLookAtPacketData
    ): ClientboundPlayerLookAtPacket {

        packetObj.entity.setPos(packetObj.x, packetObj.y, packetObj.z)

        val fromAnchor = EntityAnchorArgument.Anchor.valueOf(packetObj.fromAnchor.name)
        val toAnchor = EntityAnchorArgument.Anchor.valueOf(packetObj.toAnchor.name)

        return ClientboundPlayerLookAtPacket(
            fromAnchor, packetObj.entity, toAnchor
        )
    }
}