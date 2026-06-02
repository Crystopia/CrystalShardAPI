package net.crystopia.crystalshard.dhl.versions.v1_21_10.client


import net.crystopia.crystalshard.dhl.shared.data.packets.client.ClientboundPlayerCombatKillPacketData
import net.crystopia.crystalshard.dhl.shared.interfaces.packets.IClientPacket
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket

class Shard_ClientboundPlayerCombatKillPacket : IClientPacket<ClientboundPlayerCombatKillPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerCombatKillPacketData
    ): ClientboundPlayerCombatKillPacket {
        return ClientboundPlayerCombatKillPacket(
            packetObj.entityId,
            packetObj.message
        )
    }
}