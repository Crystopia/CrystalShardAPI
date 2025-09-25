package net.crystopia.crystalshard.config

import com.google.common.base.Preconditions
import kotlinx.serialization.ExperimentalSerializationApi

import java.io.File

object ConfigManager {

    @OptIn(ExperimentalSerializationApi::class)
    var configs: MutableMap<String, Pair<ConfigType, File>> = mutableMapOf()

    inline fun <reified T : Any> load(key: String, default: T, type: ConfigType?, file: File?): T? {

        if (!configs.contains(key)) {
            Preconditions.checkArgument(type == null, "No ConfigType is defined!");
            Preconditions.checkArgument(file == null, "No FIle location is defined!");
            configs[key] = Pair(type!!, file!!)
        }

        if (configs[key]!!.first == ConfigType.YAML) {
            return configs[key]!!.second.loadYAMLConfig(default)
        } else if (configs[key]!!.first == ConfigType.JSON) {
            return configs[key]!!.second.loadJSONConfig(default)
        }
        return null
    }

    inline fun <reified T : Any> save(key: String, default: T) {
        if (configs[key]!!.first == ConfigType.YAML) {
            configs[key]!!.second.saveYAMLConfig(default)
        } else if (configs[key]!!.first == ConfigType.JSON) {
            configs[key]!!.second.saveJSONConfig(default)
        }
    }

    inline fun <reified T : Any> reload(key: String, default: T): T? {
        if (configs[key]!!.first == ConfigType.YAML) {
            return loadYAMLFromFile(configs[key]!!.second)
        } else if (configs[key]!!.first == ConfigType.JSON) {
            return loadJSONFromFile(configs[key]!!.second)
        }
        return null
    }
}
