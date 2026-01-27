package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.teams

import net.minecraft.world.scores.Team

enum class NameTagVisibility(val id: Team.Visibility) {
    ALWAYS(Team.Visibility.ALWAYS),
    NEVER(Team.Visibility.NEVER),
    HIDE_FOR_OTHER_TEAMS(Team.Visibility.HIDE_FOR_OTHER_TEAMS),
    HIDE_FOR_OWN_TEAM(Team.Visibility.HIDE_FOR_OWN_TEAM);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.teams.NameTagVisibility): NameTagVisibility {
            return valueOf(
                type.name
            )
        }
    }

}