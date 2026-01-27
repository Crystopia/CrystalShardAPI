package net.crystopia.crystalshard.paper.dhl.shared.data.teams

import io.papermc.paper.adventure.PaperAdventure
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.CollisionRule
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.NameTagVisibility
import net.crystopia.crystalshard.paper.dhl.shared.enums.teams.TeamFlags
import net.kyori.adventure.text.Component
import net.minecraft.ChatFormatting
import net.minecraft.world.scores.PlayerTeam
import net.minecraft.world.scores.Scoreboard
import java.lang.reflect.Member

data class Team(
    var name: String,
    var teamDisplayName: Component,
    var friendlyFlags: MutableList<TeamFlags>,
    var nameTagVisibility: NameTagVisibility,
    var collisionRule: CollisionRule,
    var teamColor: Char,
    var teamPrefix: Component,
    var teamSuffix: Component,
    var members: MutableList<String>
)