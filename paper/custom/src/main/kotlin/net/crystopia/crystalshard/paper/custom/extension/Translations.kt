package net.crystopia.crystalshard.paper.custom.extension

import net.crystopia.crystalshard.paper.custom.i8n.ITranslationDataEntry

fun <T : ITranslationDataEntry> MutableMap<String, String>.toTranslationEntry(fallbackLang: String, factory: (MutableMap<String, String>, String) -> T): T = factory(this, fallbackLang)