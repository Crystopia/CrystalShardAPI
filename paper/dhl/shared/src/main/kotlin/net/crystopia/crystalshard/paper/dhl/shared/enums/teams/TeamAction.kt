package net.crystopia.crystalshard.paper.dhl.shared.enums.teams

import net.crystopia.crystalshard.paper.dhl.shared.data.packets.ClientboundSetPlayerTeamPacketData
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket

enum class TeamAction {
    ADD,
    REMOVE,
    UPDATE
}