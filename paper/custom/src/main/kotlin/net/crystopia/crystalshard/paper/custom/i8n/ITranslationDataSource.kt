package net.crystopia.crystalshard.paper.custom.i8n

interface ITranslationDataSource {

    var data: MutableMap<String, ITranslationDataEntry>

    fun init(): Unit

    fun getTranslation(lang: String, key: String): String

}