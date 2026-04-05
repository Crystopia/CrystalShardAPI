package net.crystopia.crystalshard.paper.custom.i8n

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.Context
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.crystopia.crystalshard.paper.custom.extension.parsePlaceholders

/**
 * A pre-made MiniMessage resolver to resolve given placeholders
 *
 * Usage:
 * @see String.parsePlaceholders
 */
class PlaceholderResolver(
    private val placeholders: Map<String, String>
) : TagResolver {

    override fun resolve(
        name: String,
        arguments: ArgumentQueue,
        ctx: Context
    ): Tag? {
        val key = name.lowercase()
        val value = placeholders[key] ?: return null
        return Tag.inserting { Component.text(value) }
    }

    override fun has(name: String): Boolean {
        return placeholders.containsKey(name.lowercase())
    }

    fun deserialize(input: String): Component {
        return MiniMessage.builder().tags(TagResolver.builder().resolver(this).build()).build().deserialize(input)
    }

}