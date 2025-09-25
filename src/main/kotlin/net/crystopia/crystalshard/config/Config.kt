package net.crystopia.crystalshard.config

import java.io.File

class Config(
    val file: File, val type: ConfigType? = ConfigType.JSON
) {

    // TODO: Change data to a type.

    var data: Any
        get() = data
        set(value) {
            if (type == ConfigType.JSON) {
                file.loadJSONConfig(value)
            } else if (type == ConfigType.YAML) {
                file.loadYAMLConfig(value)
            }
        }

    inline fun <reified T : Any> load(default: T): T {
        return when (type) {
            ConfigType.JSON -> {
                data = file.loadJSONConfig(default)
            }

            ConfigType.YAML -> {
                data = file.loadYAMLConfig(default)
            }

            null -> throw Exception("Please define a ConfigType!")
        } as T
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
        data = when (type) {
            ConfigType.JSON -> {
                loadJSONFromFile(file)
            }

            ConfigType.YAML -> {
                loadYAMLFromFile(file)
            }

            null -> throw Exception("Please define a ConfigType!")
        }
    }
}