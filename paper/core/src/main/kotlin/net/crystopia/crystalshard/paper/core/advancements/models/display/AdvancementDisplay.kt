package net.crystopia.crystalshard.paper.core.advancements.models.display

import io.papermc.paper.advancement.AdvancementDisplay
import kotlinx.serialization.Serializable
import net.kyori.adventure.text.Component

@Serializable
data class AdvancementDisplay(
    var icon: net.crystopia.crystalshard.paper.core.advancements.models.display.AdvancementDisplayIcon,
    /**
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.kyori.adventure.text.serializer.json.JSONComponentSerializer
     */
    var title: String,
    /**
     * eg. `JSONComponentSerializer.builder().build().serialize(Component.text("Cool text"))`
     * @see net.kyori.adventure.text.serializer.json.JSONComponentSerializer
     */
    var description: String,
    var frame: AdvancementDisplay.Frame = AdvancementDisplay.Frame.TASK,
    /**
     * eg. minecraft:gui/advancements/backgrounds/adventure
     */
    var background: String = "minecraft:gui/advancements/backgrounds/adventure",
    var show_toast: Boolean,
    var announce_to_chat: Boolean,
    var hidden: Boolean
)
