package net.crystopia.crystalshard.common.config

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.mamoe.yamlkt.Yaml
import java.io.File

val json = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
    encodeDefaults = true
}

inline fun <reified T : Any> File.loadJSONConfig(default: T): T {
    return if (exists()) {
        try {
            json.decodeFromString<T>(readText())
        } catch (_: Exception) {
            saveJSONConfig(default)
            default
        }
    } else {
        saveJSONConfig(default)
        default
    }
}

inline fun <reified T : Any> File.saveJSONConfig(config: T) {
    if (!exists() && parentFile != null) parentFile.mkdirs()
    writeText(json.encodeToString<T>(config))
}


inline fun <reified T : Any> File.loadYAMLConfig(default: T): T {
    return if (exists()) {
        try {
            Yaml.decodeFromString(readText())
        } catch (_: Exception) {
            saveJSONConfig(default)
            default
        }
    } else {
        saveJSONConfig(default)
        default
    }
}

inline fun <reified T : Any> File.saveYAMLConfig(config: T) {
    if (!exists() && parentFile != null) parentFile.mkdirs()
    writeText(Yaml.encodeToString<T>(config))
}


inline fun <reified T : Any> loadJSONFromFile(file: File): T {
    return Json.decodeFromString<T>(file.readText())
}

inline fun <reified T : Any> loadYAMLFromFile(file: File): T {
    return Yaml.decodeFromString<T>(file.readText())
}

