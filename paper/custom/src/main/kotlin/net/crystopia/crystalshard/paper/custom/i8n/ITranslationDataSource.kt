package net.crystopia.crystalshard.paper.custom.i8n

/**
 * An interface for a data storage source for translations.
 *
 * It contains all translations in a MutableMap with a String as the language and an ITranslationDataEntry for storing the translation keys and values.
 * @see ITranslationDataEntry
 */
interface ITranslationDataSource {

    var data: MutableMap<String, ITranslationDataEntry>

    fun init(): Unit

    fun getTranslation(lang: String, key: String): String

}