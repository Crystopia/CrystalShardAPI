package net.crystopia.crystalshard.utils

import net.crystopia.crystalshard.extension.CrystalPlayer
import org.bukkit.NamespacedKey

/**
 * 
 * Utility Methods to send and get cookies from a Player on a Minecraft Server.
 *
 */
object Cookie {


    /**
     * 
     * Store a Cookie in the Player with you plugin
     *
     */
    fun storeCookie(cookie: String, player: CrystalPlayer, key: NamespacedKey) {
        val cookieBytes = cookie.toByteArray(Charsets.UTF_8)
        player.handle.storeCookie(key, cookieBytes)
    }

    /**
     *
     * Get your custom Cookie from the Player
     *
     */
    fun getCookie(player: CrystalPlayer, key: NamespacedKey, callback: (String?) -> Unit) {
        player.handle.retrieveCookie(key).thenAccept { bytes ->
            if (bytes == null) {
                return@thenAccept
            }
            val string = String(bytes, Charsets.UTF_8)
            callback(string)
        }.exceptionally {
            it.printStackTrace()
            callback(null)
            null
        }
    }

}