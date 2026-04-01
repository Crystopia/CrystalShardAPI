package net.crystopia.crystalshard.paper.custom.advancements.models.display

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

@Serializable
data class AdvancementDisplay(
    var icon: AdvancementDisplayIcon,
    /**
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.crystopia.crystalshard.common.extension.toJsonElement
     */
    var title: JsonElement,
    /**
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.crystopia.crystalshard.common.extension.toJsonElement
     */
    var description: JsonElement,
    var frame: io.papermc.paper.advancement.AdvancementDisplay.Frame = AdvancementDisplay.Frame.TASK,
    /**
     * A Texture in a Resource pack....
     * eg. minecraft:gui/advancements/backgrounds/adventure
     */
    var background: String = "minecraft:gui/advancements/backgrounds/adventure",
    var show_toast: Boolean,
    var announce_to_chat: Boolean,
    var hidden: Boolean
)
