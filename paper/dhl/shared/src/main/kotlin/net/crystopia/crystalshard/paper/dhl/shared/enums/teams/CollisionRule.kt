package net.crystopia.crystalshard.paper.dhl.shared.enums.teams

import net.minecraft.world.scores.Team

enum class CollisionRule {
    ALWAYS(), NEVER(), PUSH_OTHER_TEAMS(), PUSH_OWN_TEAM();
}