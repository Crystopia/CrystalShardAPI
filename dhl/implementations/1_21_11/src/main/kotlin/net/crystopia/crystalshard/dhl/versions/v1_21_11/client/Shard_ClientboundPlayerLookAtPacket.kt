package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerLookAtPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket

class Shard_ClientboundPlayerLookAtPacket : IPacket<ClientboundPlayerLookAtPacketData> {

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