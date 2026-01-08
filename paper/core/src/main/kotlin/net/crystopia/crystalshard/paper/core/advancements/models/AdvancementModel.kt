package net.crystopia.crystalshard.paper.core.advancements.models

import kotlinx.serialization.Serializable
import net.crystopia.crystalshard.paper.core.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.core.advancements.models.display.AdvancementDisplay
import net.crystopia.crystalshard.paper.core.advancements.models.rewards.AdvancementRewards

@Serializable
data class AdvancementModel(
    /**
     * eg. minecraft:adventure/kill_all_mobs
     */
    var parent: String? = null,
    var display: net.crystopia.crystalshard.paper.core.advancements.models.display.AdvancementDisplay,
    var criteria: MutableMap<String, net.crystopia.crystalshard.paper.core.advancements.models.criteria.AdvancementCriteria> = mutableMapOf(),
    var requirements: MutableList<MutableList<String>> = mutableListOf(),
    var rewards: net.crystopia.crystalshard.paper.core.advancements.models.rewards.AdvancementRewards? = null,
    var sends_telemetry_event: Boolean = false
)
