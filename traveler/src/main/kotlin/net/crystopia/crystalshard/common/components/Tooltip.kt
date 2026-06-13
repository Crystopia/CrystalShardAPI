package net.crystopia.crystalshard.common.components

import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import java.util.*

fun ComponentBuilder<*, *>.itemTooltip(
    itemKey: String, count: Int, dataComponents: MutableMap<Key, DataComponentValue>? = null
): ComponentBuilder<*, *> {
    return hoverEvent(dataComponents?.let {
        HoverEvent.showItem(
            Key.key(itemKey), count, it
        )
    })
}

fun Component.itemTooltip(
    itemKey: String, count: Int, dataComponents: MutableMap<Key, DataComponentValue>? = null
): Component {
    return hoverEvent(dataComponents?.let {
        HoverEvent.showItem(
            Key.key(itemKey), count, it
        )
    })
}

fun ComponentBuilder<*, *>.textTooltip(
    text: Component
): ComponentBuilder<*, *> {
    return hoverEvent(HoverEvent.showText(text))
}

fun Component.textTooltip(
    text: Component
): Component {
    return hoverEvent(HoverEvent.showText(text))
}

fun ComponentBuilder<*, *>.entityTooltip(
    type: String, uuid: UUID
): ComponentBuilder<*, *> {
    return hoverEvent(HoverEvent.showEntity(Key.key(type), uuid))
}

fun Component.entityTooltip(
    type: String, uuid: UUID
): Component {
    return hoverEvent(HoverEvent.showEntity(Key.key(type), uuid))
}