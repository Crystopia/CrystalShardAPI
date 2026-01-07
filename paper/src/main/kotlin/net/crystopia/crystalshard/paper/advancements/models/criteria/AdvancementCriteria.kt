package net.crystopia.crystalshard.extras.advancements.models.criteria

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AdvancementCriteria(
    /**
     * Please read more here about the conditions:
     *
     * [Minecraft Criteria Conditions](https://minecraft.wiki/w/Advancement_definition#Criteria)
     *
     * [How to make...](https://misode.github.io/advancement/)
     */
    var conditions: JsonElement,
    /**
     * @see CriteriaTrigger
     */
    var trigger: String,
)