package net.crystopia.crystalshard.paper.custom.messaging

import net.crystopia.crystalshard.paper.core.utils.Log
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream

object MessagingUtil {


    fun createMessage() {

    }

    fun sendMessageToProxyAsPlayer(plugin: JavaPlugin, channel: String, key: String, message: String, player: Player) {
        try {
            val byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
            val out = DataOutputStream(byteArray)

            out.writeUTF(key)
            out.writeUTF(message)

            player.sendPluginMessage(plugin, channel, byteArray.toByteArray())
            out.close()
        } catch (e: Exception) {
            Log.error(e.message.toString())
        }
    }

    fun sendMessageToProxyAsServer(plugin: JavaPlugin, channel: String, key: String, message: String) {
        try {
            val byteArray: ByteArrayOutputStream = ByteArrayOutputStream()
            val out = DataOutputStream(byteArray)

            out.writeUTF(key)
            out.writeUTF(message)

            plugin.server.sendPluginMessage(plugin, channel, byteArray.toByteArray())
            out.close()
        } catch (e: Exception) {
            Log.error(e.message.toString())
        }
    }
}