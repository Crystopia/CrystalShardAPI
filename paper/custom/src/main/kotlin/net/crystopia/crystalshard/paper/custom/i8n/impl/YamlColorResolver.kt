package net.crystopia.crystalshard.paper.custom.i8n.impl

import net.crystopia.crystalshard.paper.custom.i8n.IColorResolver
import net.kyori.adventure.text.format.TextColor
import kotlin.collections.forEach

open class YamlColorResolver() : IColorResolver {

    override var colors: MutableMap<String, TextColor> = mutableMapOf()

    override fun init(data: Map<String, String>) {
        data.keys.forEach { key -> colors[key] = TextColor.fromHexString(data[key]!!)?: TextColor.color(255, 255, 255); onInit(key, TextColor.fromHexString(data[key]!!)?: TextColor.color(255, 255, 255)) }
    }

    /**
     * Override this method to allow for stuff like logging or custom behavior when initializing a color
     */
    open fun onInit(key: String, color: TextColor) {}
}