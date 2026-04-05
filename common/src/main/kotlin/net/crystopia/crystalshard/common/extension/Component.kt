package net.crystopia.crystalshard.common.extension

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.text.BuildableComponent
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
fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toPlainText(
): String {
    return PlainTextComponentSerializer.plainText().serialize(this.build())
}

fun Component.toJson(): String {
    return JSONComponentSerializer.builder().build().serialize(this)
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toJson(): String {
    return JSONComponentSerializer.builder().build().serialize(this.build())
}

fun Component.toLegacy(): String {
    return LegacyComponentSerializer.builder().build().serialize(this)
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toLegacy(
): String {
    return LegacyComponentSerializer.builder().build().serialize((this.build()))
}

fun Component.toGson(): String {
    return GsonComponentSerializer.builder().build().serialize(this)
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toGson(): String {
    return GsonComponentSerializer.builder().build().serialize((this.build()))
}

fun Component.toJsonElement(): JsonElement {
    return Json.decodeFromString(JSONComponentSerializer.builder().build().serialize(this))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.toJsonElement(): JsonElement {
    return Json.decodeFromString(JSONComponentSerializer.builder().build().serialize(this.build()))
}


fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.text(
    text: String, font: String? = null
): B {
    return append(
        MINI_MESSAGE.deserialize(text).font(font?.let { Key.key(it) })
    )
}


fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.text(
    text: String, font: String? = null, callback: ComponentBuilder<*, *>.() -> Unit = {}
): B {
    val component =
        Component.text().append(MINI_MESSAGE.deserialize(text)).font(font?.let { Key.key(it) }).also { it.callback() }
            .build()

    return append(component)
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.font(
    font: String
): B = font(Key.key(font))

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.click(
    callback: Audience.() -> Unit = {}
): B {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openUrl(
    callback: Audience.() -> Unit = {}
): B {
    return clickEvent(ClickEvent.callback { audience ->
        callback(audience)
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openFile(
    file: String
): B {
    return clickEvent(ClickEvent.openFile(file))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.openUrl(
    url: String
): B {
    return clickEvent(ClickEvent.openUrl(url))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.runCommand(
    command: String
): B {
    return clickEvent(ClickEvent.runCommand(command))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.suggestCommand(
    command: String
): B {
    return clickEvent(ClickEvent.suggestCommand(command))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.copyToClipboard(
    text: String
): B {
    return clickEvent(ClickEvent.copyToClipboard(text))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.itemTooltip(
    itemKey: String, count: Int, dataComponents: MutableMap<Key, DataComponentValue>? = null
): B {
    return hoverEvent(dataComponents?.let {
        HoverEvent.showItem(
            Key.key(itemKey), count, it
        )
    })
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.textTooltip(
    text: Component
): B {
    return hoverEvent(HoverEvent.showText(text))
}

fun <C : BuildableComponent<C, B>, B : ComponentBuilder<C, B>> ComponentBuilder<C, B>.entityTooltip(
    type: String, uuid: UUID
): B {
    return hoverEvent(HoverEvent.showEntity(Key.key(type), uuid))
}