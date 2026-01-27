package net.crystopia.crystalshard.paper.dhl.shared.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard

data class ClientboundSetPlayerTeamPacketData(
    var action: TeamAction,
    var team: Team,
)