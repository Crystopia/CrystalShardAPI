package net.crystopia.crystalshard.paper.custom.i8n

import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.minimessage.Context
import net.kyori.adventure.text.minimessage.tag.Tag
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver

/**
 * An interface that offers an almost complete custom color resolver for MiniMessage
 *
 * You only need to insert the data from a source of your choice into the colors Map, as an example:
 * @see net.crystopia.crystalshard.paper.custom.i8n.impl.YamlColorResolver
 */
interface IColorResolver : TagResolver {

    var colors: MutableMap<String, TextColor>

    fun init(data: Map<String, String>)

    override fun resolve(name: String, arguments: ArgumentQueue, ctx: Context): Tag? {
        val color: TextColor = colors[name] ?: return null
        return Tag.styling(color)
    }

    override fun has(name: String): Boolean {
        return colors.containsKey(name)
    }

}