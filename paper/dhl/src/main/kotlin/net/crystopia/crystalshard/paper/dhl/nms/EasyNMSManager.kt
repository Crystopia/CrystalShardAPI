package net.crystopia.crystalshard.paper.dhl.nms

import net.crystopia.crystalshard.paper.dhl.shared.enums.server.SoftwareType
import net.crystopia.crystalshard.paper.core.utils.Log
import net.crystopia.crystalshard.paper.dhl.ServerUtil
import org.bukkit.Bukkit

/**
 *
 * Helper Object to integrate NMS in a Plugin.
 *
 */

@Suppress("UNCHECKED_CAST")
class EasyNMSManager<T : Any> : INMSManager<T> {

    override val mcVersion: String
        get() = Bukkit.getServer().minecraftVersion

    override val classVersion: String
        get() = Bukkit.getServer().minecraftVersion.replace(".", "_")

    override lateinit var handler: T

    override fun setup(classPattern: String) {
        try {
            handler = Class.forName(classPattern.replace("{version}", classVersion))
                .getConstructor().newInstance() as T
            Log.info("Loaded NMS for Minecraft Version ${mcVersion}.")
            Log.info("CrystalShard Loaded your NMS Handler!")
        } catch (e: Exception) {
            Log.error("Cant setup NMS for your Plugin")
        }
    }


    override fun software(): SoftwareType {
        return when {
            ServerUtil.isClass("com.destroystokyo.paper.PaperConfig") ||
                    ServerUtil.isClass("io.papermc.paper.configuration.Configuration") -> SoftwareType.PAPER

            // isClass("") -> SoftwareType.FOLIA

            ServerUtil.isClass("org.spigotmc.SpigotConfig") -> SoftwareType.SPIGOT

            ServerUtil.isClass("org.bukkit.Bukkit") -> SoftwareType.BUKKIT

            else -> SoftwareType.UNKNOWN
        }
    }

   
}
