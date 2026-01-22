package net.crystopia.crystalshard.paper.dhl.shared.enums.teams

import net.minecraft.world.scores.Team

enum class CollisionRule(val id : Team.CollisionRule) {
    ALWAYS(Team.CollisionRule.ALWAYS),
    NEVER(Team.CollisionRule.NEVER),
    PUSH_OTHER_TEAMS(Team.CollisionRule.PUSH_OTHER_TEAMS),
    PUSH_OWN_TEAM(Team.CollisionRule.PUSH_OWN_TEAM);
}