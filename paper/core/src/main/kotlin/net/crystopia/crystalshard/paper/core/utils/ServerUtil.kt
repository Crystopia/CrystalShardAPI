package net.crystopia.crystalshard.paper.core.utils

import net.crystopia.crystalshard.paper.shared.enums.server.ServerVersion
import net.crystopia.crystalshard.paper.shared.enums.server.SoftwareType
import org.bukkit.Bukkit

object ServerUtil {

    fun getServerSoftware(): SoftwareType {
        return when {
            isClass("com.destroystokyo.paper.PaperConfig") ||
                    isClass("io.papermc.paper.configuration.Configuration") -> SoftwareType.PAPER

            // isClass("") -> SoftwareType.FOLIA

            isClass("org.spigotmc.SpigotConfig") -> SoftwareType.SPIGOT

            isClass("org.bukkit.Bukkit") -> SoftwareType.BUKKIT

            else -> SoftwareType.UNKNOWN
        }
    }

    fun currentVersion(): ServerVersion {
        return ServerVersion.getServerVersionByVersion(Bukkit.getMinecraftVersion())!!
    }

    fun isServerVersionSupported(): Boolean {
        return ServerVersion.getServerVersionByVersion(Bukkit.getMinecraftVersion()) != ServerVersion.UNKNOWN
    }

    fun isClass(className: String): Boolean {
        return try {
            Class.forName(className)
            true
        } catch (_: ClassNotFoundException) {
            false
        }
    }
}