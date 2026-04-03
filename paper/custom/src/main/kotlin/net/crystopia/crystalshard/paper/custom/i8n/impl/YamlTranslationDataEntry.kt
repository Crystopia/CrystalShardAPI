package net.crystopia.crystalshard.paper.custom.i8n.impl

import net.crystopia.crystalshard.paper.custom.i8n.ITranslationDataEntry
import net.crystopia.crystalshard.paper.custom.i8n.TranslationNotFoundException

open class YamlTranslationDataEntry(
    override var entry: MutableMap<String, String>,
    override val fallbackLang: String
) : ITranslationDataEntry {

    override fun getTranslation(key: String): String {
        if (entry.containsKey(key)) {
            val value = entry[key]!!
            onAttemptTranslation(key, true, value)
            return value
        } else {
            onAttemptTranslation(key, false, null)
            throw TranslationNotFoundException()
        }
    }

    /**
     * Override this method to allow for logging or other custom behavior when attempting a translation
     *
     * @param success returns true if there was a match to the key and false if not
     */
    open fun onAttemptTranslation(key: String, success: Boolean, value: String?) {}

}