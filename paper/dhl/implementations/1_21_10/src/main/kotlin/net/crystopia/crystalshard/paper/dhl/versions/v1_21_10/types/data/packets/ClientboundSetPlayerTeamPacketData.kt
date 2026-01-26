package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.types.data.packets

import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamAction
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard

data class ClientboundSetPlayerTeamPacketData(
    var action: TeamAction,
    var team: Team,
) {
    fun build(): ClientboundSetPlayerTeamPacket {





        return when (action) {
            TeamAction.ADD -> {
                ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(
                    team.build(),true
                )
            }

            TeamAction.REMOVE -> {
                ClientboundSetPlayerTeamPacket.createRemovePacket(
                    team.build(),
                )
            }

            TeamAction.UPDATE -> {
                ClientboundSetPlayerTeamPacket.createAddOrModifyPacket(
                    team.build(),
                    false
                )
            }
        }

    }
}
