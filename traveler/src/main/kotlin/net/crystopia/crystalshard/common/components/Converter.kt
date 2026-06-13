package net.crystopia.crystalshard.common.components

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentBuilder
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.kyori.adventure.text.serializer.json.JSONComponentSerializer
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

val MINI_MESSAGE: MiniMessage = MiniMessage.miniMessage()

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


