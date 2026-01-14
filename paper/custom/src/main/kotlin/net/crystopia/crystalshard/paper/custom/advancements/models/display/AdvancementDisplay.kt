package net.crystopia.crystalshard.paper.custom.advancements.models.display

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable

@Serializable
data class AdvancementDisplay(
    var icon: AdvancementDisplayIcon,
    /**
     * TODO: Change to Component and serialize it internally
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.kyori.adventure.text.serializer.json.JSONComponentSerializer
     */
    var title: String,
    /**
     * TODO: Change to Component and serialize it internally
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.kyori.adventure.text.serializer.json.JSONComponentSerializer
     */
    var description: String,
    var frame: io.papermc.paper.advancement.AdvancementDisplay.Frame = AdvancementDisplay.Frame.TASK,
    /**
     * eg. minecraft:gui/advancements/backgrounds/adventure
     */
    var background: String = "minecraft:gui/advancements/backgrounds/adventure",
    var show_toast: Boolean,
    var announce_to_chat: Boolean,
    var hidden: Boolean
)
