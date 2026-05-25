package net.crystopia.crystalshard.dhl.versions.v1_21_11.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerCombatKillPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket

class Shard_ClientboundPlayerCombatKillPacket : IPacket<ClientboundPlayerCombatKillPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerCombatKillPacketData
    ): ClientboundPlayerCombatKillPacket {
        return ClientboundPlayerCombatKillPacket(
            packetObj.entityId,
            packetObj.message
        )
    }
}