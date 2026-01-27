package net.crystopia.crystalshard.paper.dhl.shared.data.packets.client

import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction

data class ClientboundSetPlayerTeamPacketData(
    var action: TeamAction,
    var team: Team,
)