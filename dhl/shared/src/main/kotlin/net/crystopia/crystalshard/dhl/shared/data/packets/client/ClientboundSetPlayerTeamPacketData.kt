package net.crystopia.crystalshard.dhl.shared.data.packets.client

import net.crystopia.crystalshard.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamAction

data class ClientboundSetPlayerTeamPacketData(
    var action: TeamAction,
    var team: Team,
)