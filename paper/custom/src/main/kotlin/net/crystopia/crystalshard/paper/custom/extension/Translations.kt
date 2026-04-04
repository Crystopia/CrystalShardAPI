package net.crystopia.crystalshard.paper.custom.extension

import net.crystopia.crystalshard.paper.custom.i8n.ITranslationDataEntry
import net.crystopia.crystalshard.paper.custom.i8n.PlaceholderResolver
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

fun <T : ITranslationDataEntry> MutableMap<String, String>.toTranslationEntry(fallbackLang: String, factory: (MutableMap<String, String>, String) -> T): T = factory(this, fallbackLang)

fun String.parsePlaceholders(placeholders: Map<String, String>): String = PlainTextComponentSerializer.plainText().serialize(PlaceholderResolver(placeholders).deserialize(this))