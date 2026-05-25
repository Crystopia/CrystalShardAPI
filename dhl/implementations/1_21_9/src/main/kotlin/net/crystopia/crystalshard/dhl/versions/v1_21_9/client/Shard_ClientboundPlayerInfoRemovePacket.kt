package net.crystopia.crystalshard.dhl.versions.v1_21_9.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerInfoRemovePacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket

class Shard_ClientboundPlayerInfoRemovePacket : IPacket<ClientboundPlayerInfoRemovePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerInfoRemovePacketData
    ): ClientboundPlayerInfoRemovePacket {
        return ClientboundPlayerInfoRemovePacket(packetObj.uuids)
    }
}