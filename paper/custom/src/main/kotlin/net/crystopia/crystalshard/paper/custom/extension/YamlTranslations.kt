package net.crystopia.crystalshard.paper.custom.extension

import net.crystopia.crystalshard.paper.custom.i8n.impl.YamlTranslationDataEntry
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

fun YamlConfiguration.toDataEntry(): YamlTranslationDataEntry {
    val fallbackLang: String = this.getString("fallback")!!

    val data: ConfigurationSection = this.getConfigurationSection("values")!!

    val dataMap: MutableMap<String, String> = mutableMapOf()

    data.getKeys(true).forEach { key ->
        dataMap[key] = data.getString(key)!!
    }

    return dataMap.toTranslationEntry<YamlTranslationDataEntry>(fallbackLang, ::YamlTranslationDataEntry)
}