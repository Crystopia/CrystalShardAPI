package net.crystopia.crystalshard.extras.advancements.models.display

import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component
import org.bukkit.Material

@Serializable
data class AdvancementDisplayIcon(

    /**
     * eg. ```Material.PAPER.key.toString()```
     */
    var id: String = Material.PAPER.key.toString(),
    var count: Int = 1,
    /**
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.kyori.adventure.text.serializer.json.JSONComponentSerializer
     */
    // var component: String? = null
)
