package net.crystopia.crystalshard.paper.custom.advancements.models

import kotlinx.serialization.Serializable
import net.crystopia.crystalshard.paper.custom.advancements.models.criteria.AdvancementCriteria
import net.crystopia.crystalshard.paper.custom.advancements.models.display.AdvancementDisplay
import net.crystopia.crystalshard.paper.custom.advancements.models.rewards.AdvancementRewards

@Serializable
data class AdvancementModel(
    /**
     * eg. minecraft:adventure/kill_all_mobs
     */
    var parent: String? = null,
    var display: AdvancementDisplay,
    var criteria: MutableMap<String, AdvancementCriteria> = mutableMapOf(),
    var requirements: MutableList<MutableList<String>> = mutableListOf(),
    var rewards: AdvancementRewards? = null,
    var sends_telemetry_event: Boolean = false
)
