package net.crystopia.crystalshard.common.utils

import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

object ProxyUtil {
    fun sendToServer(player: Player, serverName: String, plugin: JavaPlugin) {
        try {
            val byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
            val out = DataOutputStream(byteArray)

            out.writeUTF("Connect")
            out.writeUTF(serverName)

            player.sendPluginMessage(plugin, "BungeeCord", byteArray.toByteArray())
            out.close()
        } catch (e: Exception) {
            Log.error(e.message.toString())
        }
    }
}