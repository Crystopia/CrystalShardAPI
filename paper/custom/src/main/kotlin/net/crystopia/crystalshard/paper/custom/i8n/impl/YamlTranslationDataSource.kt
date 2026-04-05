package net.crystopia.crystalshard.paper.custom.i8n.impl

import net.crystopia.crystalshard.paper.custom.extension.toDataEntry
import net.crystopia.crystalshard.paper.custom.i8n.ITranslationDataEntry
import net.crystopia.crystalshard.paper.custom.i8n.ITranslationDataSource
import net.crystopia.crystalshard.paper.custom.i8n.TranslationNotFoundException
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

/**
 * An implementation of an ITranslationDataSource to use all YAML files from a given directory as language files.
 *
 * Each YAML file needs to have the following structure:
 *
 * ```yml
 * fallback: ""     # enter a fallback language (of course this language has to exist)
 * values:          # add all translation strings as key-value pairs in here
 *  key1: value1
 *  key2: value2
 *  ...
 * ```
 */
open class YamlTranslationDataSource(
    protected val langDirectory: File
) : ITranslationDataSource {

    override var data: MutableMap<String, ITranslationDataEntry> = mutableMapOf()

    override fun init() {
        if (langDirectory.isDirectory) {
            langDirectory.listFiles()?.forEach {
                if (it.isFile && it.name.endsWith(".yml")) {
                    val cfg = YamlConfiguration.loadConfiguration(it)
                    data[it.nameWithoutExtension] = cfg.toDataEntry()
                    onInit(it.nameWithoutExtension, cfg)
                }
            }
        }
    }

    override fun getTranslation(lang: String, key: String): String {
        if (data.containsKey(lang)) {
            try {
                return data[lang]!!.getTranslation(key)
            } catch (e: TranslationNotFoundException) {
                if (data[lang]!!.getFallbackLang() != null) {
                    return getTranslation(data[lang]!!.fallbackLang!!, key, 10)
                }
            }
        } else {
            throw TranslationNotFoundException()
        }
        return ""
    }

    fun getTranslation(lang: String, key: String, tries: Int): String {
        if (data.containsKey(lang) && tries > 0) {
            try {
                return data[lang]!!.getTranslation(key)
            } catch (e: TranslationNotFoundException) {
                if (data[lang]!!.getFallbackLang() != null) {
                    return getTranslation(data[lang]!!.fallbackLang!!, key, tries - 1)
                }
            }
        } else {
            throw TranslationNotFoundException()
        }
        return ""
    }

    /**
     * Override this method to allow for logging or other custom behavior when initializing a language
     */
    open fun onInit(name: String, data: YamlConfiguration) {}

}