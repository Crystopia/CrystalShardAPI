package net.crystopia.crystalshard.paper.dhl.types.teams

import net.crystopia.crystalshard.dhl.shared.enums.teams.CollisionRule
import net.crystopia.crystalshard.dhl.shared.enums.teams.NameTagVisibility
import net.crystopia.crystalshard.dhl.shared.enums.teams.TeamFlags
import net.kyori.adventure.text.Component

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