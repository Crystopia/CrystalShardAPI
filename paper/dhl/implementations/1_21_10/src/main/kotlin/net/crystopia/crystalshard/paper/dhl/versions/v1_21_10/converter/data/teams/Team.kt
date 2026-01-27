package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.data.teams

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.data.teams.Team
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamFlags
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.teams.CollisionRule
import net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.teams.NameTagVisibility
import net.minecraft.ChatFormatting
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard

fun Team.build(): PlayerTeam {

    val team = PlayerTeam(
        Scoreboard(),
        name
    )

    team.displayName = PaperAdventure.asVanilla(teamDisplayName)
    team.isAllowFriendlyFire = friendlyFlags.contains(TeamFlags.ALLOW_FRIENDLY_FIRE)
    if (friendlyFlags.contains(TeamFlags.CAN_SEE_INVISIBLE_PLAYERS_ON_THE_SAME_TEAM)) team.setSeeFriendlyInvisibles(true)
    else team.setSeeFriendlyInvisibles(false)
    team.nameTagVisibility = NameTagVisibility.convert(nameTagVisibility).id
    team.collisionRule = CollisionRule.convert(collisionRule).id
    team.color = ChatFormatting.getByCode(teamColor)!!
    team.playerPrefix =  PaperAdventure.asVanilla(teamPrefix)
    team.playerSuffix =  PaperAdventure.asVanilla(teamSuffix)
    team.players.addAll(members)

    return team
}