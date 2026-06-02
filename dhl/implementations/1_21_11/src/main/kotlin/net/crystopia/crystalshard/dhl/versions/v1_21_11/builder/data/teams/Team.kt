package net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.data.teams

import net.crystopia.crystalshard.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamFlags
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.enums.teams.CollisionRule
import net.crystopia.crystalshard.dhl.versions.v1_21_11.builder.enums.teams.NameTagVisibility
import net.minecraft.ChatFormatting
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard

fun Team.build(): PlayerTeam {

    val team = PlayerTeam(
        Scoreboard(),
        name
    )

    team.displayName = teamDisplayName
    team.isAllowFriendlyFire = friendlyFlags.contains(TeamFlags.ALLOW_FRIENDLY_FIRE)
    if (friendlyFlags.contains(TeamFlags.CAN_SEE_INVISIBLE_PLAYERS_ON_THE_SAME_TEAM)) team.setSeeFriendlyInvisibles(true)
    else team.setSeeFriendlyInvisibles(false)
    team.nameTagVisibility = NameTagVisibility.convert(nameTagVisibility).id
    team.collisionRule = CollisionRule.convert(collisionRule).id
    team.color = ChatFormatting.getByCode(teamColor)!!
    team.setPlayerPrefix(teamPrefix)
    team.setPlayerSuffix(teamSuffix)
    team.players.addAll(members)

    return team
}