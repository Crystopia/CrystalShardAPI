package net.crystopia.crystalshard.paper.custom.i8n.impl.extension

import net.crystopia.crystalshard.paper.custom.i8n.PlaceholderResolver
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun String.parsePlaceholders(placeholders: Map<String, String>): String {
    return PlainTextComponentSerializer.plainText().serialize(PlaceholderResolver(placeholders).deserialize(this))
}