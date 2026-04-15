package net.crystopia.crystalshard.common.extension

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.DataComponentValue
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import java.util.*

val MINI_MESSAGE: MiniMessage = MiniMessage.miniMessage()

fun cmpb(string: String): ComponentBuilder<*, *> {
   return Component.text().text(string)
}

fun cmp(string: String): Component {
    return MINI_MESSAGE.deserialize(string)
}

fun Component.toPlainText(): String {
    return PlainTextComponentSerializer.plainText().serialize(this)
}
fun ComponentBuilder<*, *>.toPlainText(
): String {
    return PlainTextComponentSerializer.plainText().serialize(this.build())
}

fun Component.toJson(): String {
    return JSONComponentSerializer.builder().build().serialize(this)
}

fun ComponentBuilder<*, *>.toJson(): String {
    return JSONComponentSerializer.builder().build().serialize(this.build())
}

fun Component.toLegacy(): String {
    return LegacyComponentSerializer.builder().build().serialize(this)
}

fun ComponentBuilder<*, *>.toLegacy(
): String {
    return LegacyComponentSerializer.builder().build().serialize((this.build()))
}

fun Component.toGson(): String {
    return GsonComponentSerializer.builder().build().serialize(this)
}

fun ComponentBuilder<*, *>.toGson(): String {
    return GsonComponentSerializer.builder().build().serialize((this.build()))
}

fun Component.toJsonElement(): JsonElement {
    return Json.decodeFromString(JSONComponentSerializer.builder().build().serialize(this))
}

fun ComponentBuilder<*, *>.toJsonElement(): JsonElement {
    return Json.decodeFromString(JSONComponentSerializer.builder().build().serialize(this.build()))
}


fun ComponentBuilder<*, *>.text(
    text: String, font: String? = null
): ComponentBuilder<*, *> {
    return append(
        MINI_MESSAGE.deserialize(text).font(font?.let { Key.key(it) })
    )
}


fun ComponentBuilder<*, *>.text(
    text: String, font: String? = null, callback: ComponentBuilder<*, *>.() -> Unit = {}
): ComponentBuilder<*, *> {
    val component =
        Component.text().append(MINI_MESSAGE.deserialize(text)).font(font?.let { Key.key(it) }).also { it.callback() }
            .build()

    return append(component)
}

fun ComponentBuilder<*, *>.font(
    font: String
): ComponentBuilder<*, *> = font(Key.key(font))

fun ComponentBuilder<*, *>.click(
    callback: Audience.() -> Unit = {}
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun ComponentBuilder<*, *>.openUrl(
    callback: Audience.() -> Unit = {}
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun ComponentBuilder<*, *>.openFile(
    file: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.openFile(file))
}

fun ComponentBuilder<*, *>.openUrl(
    url: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.openUrl(url))
}

fun ComponentBuilder<*, *>.runCommand(
    command: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.runCommand(command))
}

fun ComponentBuilder<*, *>.suggestCommand(
    command: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.suggestCommand(command))
}

fun ComponentBuilder<*, *>.copyToClipboard(
    text: String
): ComponentBuilder<*, *> {
    return clickEvent(ClickEvent.copyToClipboard(text))
}

fun ComponentBuilder<*, *>.itemTooltip(
    itemKey: String, count: Int, dataComponents: MutableMap<Key, DataComponentValue>? = null
): ComponentBuilder<*, *> {
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

fun ComponentBuilder<*, *>.entityTooltip(
    type: String, uuid: UUID
): ComponentBuilder<*, *> {
    return hoverEvent(HoverEvent.showEntity(Key.key(type), uuid))
}