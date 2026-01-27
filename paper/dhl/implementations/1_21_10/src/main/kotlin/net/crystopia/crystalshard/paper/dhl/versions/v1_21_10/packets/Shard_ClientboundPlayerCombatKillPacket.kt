package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.packets


import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.packets.client.ClientboundPlayerCombatKillPacketData
import net.crystopia.crystalshard.paper.dhl.shared.interfaces.packets.IPacket
import net.minecraft.network.protocol.game.ClientboundPlayerCombatKillPacket

class Shard_ClientboundPlayerCombatKillPacket : IPacket<ClientboundPlayerCombatKillPacketData> {

    override fun createPacket(
        packetObj: ClientboundPlayerCombatKillPacketData
    ): ClientboundPlayerCombatKillPacket {
        return ClientboundPlayerCombatKillPacket(
            packetObj.entityId,
            PaperAdventure.asVanilla(packetObj.message)
        )
    }
}