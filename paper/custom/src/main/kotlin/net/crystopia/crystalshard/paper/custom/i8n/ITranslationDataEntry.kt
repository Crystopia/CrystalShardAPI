package net.crystopia.crystalshard.paper.custom.i8n

interface ITranslationDataEntry {

    var entry: MutableMap<String, String>
    val fallbackLang: String

    fun getTranslation(key: String): String

    fun getFallbackLang(): String = this.fallbackLang

}