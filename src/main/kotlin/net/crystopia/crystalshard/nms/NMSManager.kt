package net.crystopia.crystalshard.nms

import net.crystopia.crystalshard.enums.SoftwareType
import net.crystopia.crystalshard.utils.Log
import org.bukkit.Bukkit

/**
 *
 * Helper Object to integrate NMS in a Plugin.
 *
 */

object NMSManager {

    val mcVersion: String
        get() = Bukkit.getServer().minecraftVersion

    val classVersion: String
        get() = Bukkit.getServer().minecraftVersion.replace(".", "_")

    var handler: NMSHandler? = null

    fun setup(classPattern: String) {
        try {
            handler = Class.forName(classPattern.replace("{version}", classVersion))
                .getConstructor().newInstance() as NMSHandler
            Log.info("Loaded NMS for Minecraft Version ${mcVersion}.")
            Log.info("CrystalShard Loaded your NMS Handler!")
            return
        } catch (e: Exception) {
            Log.error("Cant setup NMS for your Plugin")
        }
    }


    fun software(): SoftwareType {
        return when {
            isClass("com.destroystokyo.paper.PaperConfig") ||
                    isClass("io.papermc.paper.configuration.Configuration") -> SoftwareType.PAPER

            // isClass("") -> SoftwareType.FOLIA

            isClass("org.spigotmc.SpigotConfig") -> SoftwareType.SPIGOT

            isClass("org.bukkit.Bukkit") -> SoftwareType.BUKKIT

            else -> SoftwareType.UNKNOWN
        }
    }

    private fun isClass(className: String): Boolean {
        return try {
            Class.forName(className)
            true
        } catch (_: ClassNotFoundException) {
            false
        }
    }
}
