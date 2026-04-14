package net.crystopia.crystalshard.common.config

import java.io.File

inline fun <T : Any> config(
    file: File, type: ConfigType? = ConfigType.JSON, config: Config<T>.() -> Unit
): Config<T> {
    val config = Config<T>(file, type)
    config(config)
    return config
}

class Config<C : Any>(
    val file: File, val type: ConfigType? = ConfigType.JSON
) {

    lateinit var data: C

    inline fun <reified T : Any> load(default: T): T {
        return when (type) {
            ConfigType.JSON -> {
                val fileData = file.loadJSONConfig(default)
                this.data = fileData as C
                fileData as T
            }

            ConfigType.YAML -> {
                val fileData = file.loadYAMLConfig(default)
                this.data = fileData as C
                fileData as T
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

    fun reload(): Config<C> {
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