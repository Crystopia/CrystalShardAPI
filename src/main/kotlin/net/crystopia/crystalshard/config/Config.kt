package net.crystopia.crystalshard.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationStrategy
import java.io.File

class Config(
    private val file: File,
    private val dataClass: SerializationStrategy<Serializable>,
    val type: ConfigType = ConfigType.JSON
) {

    /**
     * JSON Functions to use .json Configs.
     */
    var loadJSON = file.loadConfig(dataClass)

    /**
     * YAML Functions to use .yaml Configs.
     */
    var loadYAML = Yaml.default.parseToYamlNode(file.readText())
    fun save() {
        when (type) {
            ConfigType.JSON -> {
                file.writeText(json.encodeToString(loadJSON))
            }

            ConfigType.YAML -> {
                file.writeText(
                    Yaml.default.encodeToString(
                        dataClass,
                        Yaml.default.decodeFromYamlNode(loadYAML)
                    )
                )
            }
        }
    }

    fun reload() {
        when (type) {
            ConfigType.JSON -> {
                loadJSON = loadFromFile(file)
            }

            ConfigType.YAML -> {
                loadYAML = Yaml.default.parseToYamlNode(file.readText())
            }
        }
    }
}