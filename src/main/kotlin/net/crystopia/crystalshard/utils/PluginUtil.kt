package net.crystopia.crystalshard.utils

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

object PluginUtil {

    fun requirePlugin(plugin: JavaPlugin, pluginName: String, disableThis: Boolean = false): Boolean {
        Log.info("Plugin $pluginName has been required!")

        if (plugin.server.pluginManager.getPlugin(pluginName) != null) {
            val requiredPlugin = plugin.server.pluginManager.getPlugin(pluginName)
            if (requiredPlugin?.isEnabled == true) {
                Log.info("Plugin $pluginName is enabled hooking into Plugin $pluginName")
                return true
            } else {
                Log.warn("Plugin $pluginName is not enabled. Can't use Hook!")

                if (disableThis) {
                    Log.error("Disabling plugin '${plugin.name}' because $pluginName is not enabled!")
                    plugin.server.pluginManager.disablePlugin(plugin)
                    return false
                }

                return false
            }
        } else {
            Log.warn("Plugin $pluginName has not been found on your Server!")
            return false
        }
    }

    /**
     *
     * **WARNING** - Only use this if you have no other method to load a plugin.
     *
     */
    fun loadPlugin(file: File) {
        Log.info("Loading plugin ${file.name}...")
        try {
            Bukkit.getPluginManager().loadPlugin(file)
        } catch (e: Exception) {
            Log.error("Error white loading plugin! \n${e.stackTraceToString()}")
        }
        Log.info("Loaded plugin ${file.name}")
    }

    /**
     *
     * **WARNING** - Only use this if you have no other method to enable the plugin.
     *
     */
    fun enablePlugin(name: String) {
        Log.info("Loading plugin ${name}...")
        try {
            val plugin = Bukkit.getPluginManager().getPlugin(name)
            if (plugin == null) {
                Log.error("Could not enable plugin $name")
            } else {
                Bukkit.getPluginManager().enablePlugin(plugin)
            }
        } catch (e: Exception) {
            Log.error("Error white loading plugin! \n${e.stackTraceToString()}")
        }
        Log.info("Loaded plugin $name")
    }
    
    

    fun printPluginInfo(plugin: JavaPlugin) {
        val meta = plugin.pluginMeta
        val authors = if (meta.authors.isNotEmpty()) meta.authors.joinToString(", ") else "Unknown"
        val website = meta.website ?: "—"

        val lines = listOf(
            " ",
            "${Log.GRAY}========================================${Log.RESET}",
            "${Log.LIGHTGREEN}Loaded plugin ${meta.name} for minecraft version ${plugin.server.minecraftVersion}.${Log.RESET}",
            "${Log.GRAY}   Plugin: ${meta.name}${Log.RESET}",
            "${Log.GRAY}   Version: ${meta.version}${Log.RESET}",
            "${Log.GRAY}   Author(s): $authors${Log.RESET}",
            "${Log.GRAY}   Description: ${meta.description}${Log.RESET}",
            "${Log.GRAY}   Website: $website${Log.RESET}",
            "${Log.GRAY}========================================${Log.RESET}",
            " "
        )

        lines.forEach { Log.info(it) }
    }
}