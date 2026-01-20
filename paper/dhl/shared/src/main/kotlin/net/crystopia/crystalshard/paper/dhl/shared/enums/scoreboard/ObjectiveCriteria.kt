package net.crystopia.crystalshard.paper.dhl.shared.enums.scoreboard

enum class ObjectiveCriteria(val id: net.minecraft.world.scores.criteria.ObjectiveCriteria) {
    DUMMY(net.minecraft.world.scores.criteria.ObjectiveCriteria.DUMMY), TRIGGER(net.minecraft.world.scores.criteria.ObjectiveCriteria.TRIGGER), DEATH_COUNT(
        net.minecraft.world.scores.criteria.ObjectiveCriteria.DEATH_COUNT), KILL_COUNT_PLAYERS(
        net.minecraft.world.scores.criteria.ObjectiveCriteria.KILL_COUNT_PLAYERS
    ),
    KILL_COUNT_ALL(net.minecraft.world.scores.criteria.ObjectiveCriteria.KILL_COUNT_ALL), HEALTH(net.minecraft.world.scores.criteria.ObjectiveCriteria.HEALTH), FOOD(
        net.minecraft.world.scores.criteria.ObjectiveCriteria.FOOD), AIR(
        net.minecraft.world.scores.criteria.ObjectiveCriteria.AIR
    ),
    ARMOR(net.minecraft.world.scores.criteria.ObjectiveCriteria.ARMOR), EXPERIENCE(net.minecraft.world.scores.criteria.ObjectiveCriteria.EXPERIENCE), LEVEL(
        net.minecraft.world.scores.criteria.ObjectiveCriteria.LEVEL),
    // TEAM_KILL(ObjectiveCriteria.TEAM_KILL),
    // KILLED_BY_TEAM(ObjectiveCriteria.KILLED_BY_TEAM)
}
