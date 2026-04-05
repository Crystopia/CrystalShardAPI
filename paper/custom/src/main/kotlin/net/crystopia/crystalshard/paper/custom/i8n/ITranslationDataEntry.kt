package net.crystopia.crystalshard.paper.custom.i8n

/**
 * An interface to store the translation data for one language.
 *
 * It additionally gives an option to specify a fallback language to look for the given translation key if the current data entry does not contain it,
 * although this behavior heavily depends on the implementation of the ITranslationDataSource.
 */
interface ITranslationDataEntry {

    var entry: MutableMap<String, String>
    val fallbackLang: String?

    fun getTranslation(key: String): String

    fun getFallbackLang(): String? = this.fallbackLang

}