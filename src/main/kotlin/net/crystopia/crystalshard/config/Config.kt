package net.crystopia.crystalshard.config

import java.io.File

class Config(
    val file: File, val type: ConfigType? = ConfigType.JSON
) {

    inline fun <reified T : Any> load(default: T): T {
        return when (type) {
            ConfigType.JSON -> {
                file.loadJSONConfig(default)
            }

            ConfigType.YAML -> {
                file.loadYAMLConfig(default)
            }

            null -> throw Exception("Please define a ConfigType!")
        }
    }

    inline fun <reified T : Any> save(default: T) {
        when (type) {
            ConfigType.JSON -> {
                file.saveJSONConfig(default)
            }

            ConfigType.YAML -> {
                file.saveYAMLConfig(default)
            }

            null -> throw Exception("Please define a ConfigType!")
        }
    }

    fun reload() {
        return when (type) {
            ConfigType.JSON -> {
                load(loadJSONFromFile(file))
            }

            ConfigType.YAML -> {
                load(loadYAMLFromFile(file))
            }

            null -> throw Exception("Please define a ConfigType!")
        }
    }
}