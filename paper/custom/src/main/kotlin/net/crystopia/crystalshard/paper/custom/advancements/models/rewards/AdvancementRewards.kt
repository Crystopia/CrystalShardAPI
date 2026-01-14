package net.crystopia.crystalshard.paper.custom.advancements.models.rewards

import kotlinx.serialization.Serializable

@Serializable
data class AdvancementRewards(
    var experience: Int = 0,
    /**
     * eg. ``["minecraft:acacia_shelf"]`
     */
    var recipes: MutableList<String> = mutableListOf(),
    /**
     * eg. ``["minecraft:blocks/acacia_door"]`
     */
    var loot: MutableList<String> = mutableListOf()
)
