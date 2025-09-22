package net.crystopia.crystalshard.utils

import net.crystopia.crystalshard.entities.CrystalPlayer
import org.bukkit.NamespacedKey

object Cookie {


    fun storeCookie(cookie: String, player: CrystalPlayer, key: NamespacedKey) {
        val cookieBytes = cookie.toByteArray(Charsets.UTF_8)
        player.player.storeCookie(key, cookieBytes)
    }

    fun getCookie(player: CrystalPlayer, key: NamespacedKey, callback: (String?) -> Unit) {
        player.player.retrieveCookie(key).thenAccept { bytes ->
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