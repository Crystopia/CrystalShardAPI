package net.crystopia.crystalshard.paper.core.utils

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

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
    fun set(key: NamespacedKey, cookie: String, player: Player) {
        val cookieBytes = cookie.toByteArray(Charsets.UTF_8)
        player.storeCookie(key, cookieBytes)
    }

    /**
     *
     * Get your custom Cookie from the Player
     *
     */
    fun get(key: NamespacedKey, player: Player, callback: (String?) -> Unit) {
        player.retrieveCookie(key).thenAccept { bytes ->
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