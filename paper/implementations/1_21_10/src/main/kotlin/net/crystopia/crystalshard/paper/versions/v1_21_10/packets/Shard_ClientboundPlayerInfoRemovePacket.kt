package net.crystopia.crystalshard.paper.versions.v1_21_10.packets


import net.crystopia.crystalshard.paper.shared.data.packets.ClientboundPlayerInfoRemovePacketData
import net.crystopia.crystalshard.paper.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket

class Shard_ClientboundPlayerInfoRemovePacket : IPacket<ClientboundPlayerInfoRemovePacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerInfoRemovePacketData
    ): ClientboundPlayerInfoRemovePacket {
        return ClientboundPlayerInfoRemovePacket(packetObj.uuids)
    }
}