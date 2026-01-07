package net.crystopia.crystalshard.extras.messaging

import com.google.common.io.ByteStreams
import net.crystopia.crystalshard.common.utils.Log
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener


class PluginMessage {

    var plugin: JavaPlugin
    var messageType: String
    var channel: String
    var type: ChannelType
    var listener: Listener? = null

    constructor(
        channelType: ChannelType,
        plugin: JavaPlugin,
        channel: String,
        messageType: String,
        onMessage: (
            channel: String,
            player: Player,
            message: ByteArray
        ) -> Unit
    ) {
        this.type = channelType
        this.plugin = plugin
        this.channel = channel
        this.messageType = messageType
        if (type == ChannelType.IN) {
            listener = Listener(
                channel,
                onMessage
            )
        }
    }

    fun register(): PluginMessage {
        when (type) {
            ChannelType.IN -> {
                Bukkit.getServer().messenger.registerOutgoingPluginChannel(plugin, channel)
                Bukkit.getServer().messenger.registerIncomingPluginChannel(plugin, channel, listener!!)
            }

            ChannelType.OUT -> {
                Bukkit.getServer().messenger.registerOutgoingPluginChannel(plugin, channel)
            }

            else -> throw Exception("No more options")
        }
        return this
    }

    fun unregister(): PluginMessage {
        when (type) {
            ChannelType.IN -> {
                Bukkit.getServer().messenger.unregisterIncomingPluginChannel(plugin, channel)
            }

            ChannelType.OUT -> {
                Bukkit.getServer().messenger.unregisterOutgoingPluginChannel(plugin, channel)
            }

            else -> throw Exception("No more options")
        }
        return this
    }

    fun send(message: String): PluginMessage {
        try {
            val out = ByteStreams.newDataOutput()

            out.writeUTF(messageType)
            out.writeUTF(message)

            plugin.server.sendPluginMessage(plugin, channel, out.toByteArray())
        } catch (e: Exception) {
            Log.error(e.message.toString())
        }

        return this
    }

    fun send(message: String, player: Player): PluginMessage {
        try {
            val out = ByteStreams.newDataOutput()

            out.writeUTF(messageType)
            out.writeUTF(message)

            player.sendPluginMessage(plugin, channel, out.toByteArray())
        } catch (e: Exception) {
            Log.error(e.message.toString())
        }

        return this
    }

    class Listener(
        val channel: String, val callback: (
            channel: String,
            player: Player,
            message: ByteArray,
        ) -> Unit
    ) : PluginMessageListener {
        override fun onPluginMessageReceived(
            channel: String,
            player: Player,
            message: ByteArray,
        ) {
            if (channel != this.channel) return
            callback(
                channel,
                player,
                message
            )
        }

    }
}