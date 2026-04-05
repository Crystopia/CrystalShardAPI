package net.crystopia.crystalshard.paper.custom.i8n.impl

import net.crystopia.crystalshard.paper.custom.i8n.IColorResolver
import net.kyori.adventure.text.format.TextColor
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import kotlin.collections.forEach

/**
 * An implementation of the IColorResolver to get the data for the colors from a given YAML file
 */
open class YamlColorResolver() : IColorResolver {

    override var colors: MutableMap<String, TextColor> = mutableMapOf()

    override fun init(data: Map<String, String>) {
        data.keys.forEach { key -> colors[key] = TextColor.fromHexString(data[key]!!)?: TextColor.color(255, 255, 255); onInit(key, TextColor.fromHexString(data[key]!!)?: TextColor.color(255, 255, 255)) }
    }

    fun init(file: File) {
        val map = mutableMapOf<String, String>()
        val cfg = YamlConfiguration.loadConfiguration(file)
        cfg.getKeys(true).forEach { key -> map[key] = cfg.getString(key) as String }
        init(map)
    }

    /**
     * Override this method to allow for stuff like logging or custom behavior when initializing a color
     */
    open fun onInit(key: String, color: TextColor) {}
}