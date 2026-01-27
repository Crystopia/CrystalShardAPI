package net.crystopia.crystalshard.paper.dhl.versions.v1_21_10.converter.enums.teams

import net.minecraft.world.scores.Team

enum class CollisionRule(val id : Team.CollisionRule) {
    ALWAYS(Team.CollisionRule.ALWAYS),
    NEVER(Team.CollisionRule.NEVER),
    PUSH_OTHER_TEAMS(Team.CollisionRule.PUSH_OTHER_TEAMS),
    PUSH_OWN_TEAM(Team.CollisionRule.PUSH_OWN_TEAM);

    companion object {
        fun convert(type: net.crystopia.crystalshard.paper.dhl.shared.enums.teams.CollisionRule): CollisionRule {
            return valueOf(
                type.name
            )
        }
    }

}