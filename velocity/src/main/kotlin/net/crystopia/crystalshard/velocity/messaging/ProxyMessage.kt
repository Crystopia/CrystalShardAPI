package net.crystopia.crystalshard.velocity.messaging

import com.google.common.io.ByteStreams
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PluginMessageEvent
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier
import com.velocitypowered.api.proxy.server.RegisteredServer

class ProxyMessage {

    var identifier: MinecraftChannelIdentifier
    var server: ProxyServer
    var messageType: String
    var onEvent: PluginMessageEvent.(message: ProxyMessage) -> Unit

    constructor(
        server: ProxyServer,
        identifier: MinecraftChannelIdentifier,
        messageType: String,
        onEvent: PluginMessageEvent.(message: ProxyMessage) -> Unit
    ) {
        this.identifier = identifier
        this.messageType = messageType
        this.server = server
        this.onEvent = onEvent
    }

    fun register(): ProxyMessage {
        server.channelRegistrar.register(identifier)
        return this
    }

    fun unRegister(): ProxyMessage {
        server.channelRegistrar.unregister(identifier)
        return this
    }

    fun send(message: String, server: RegisteredServer): ProxyMessage {

        val out = ByteStreams.newDataOutput()

        out.writeUTF(messageType)
        out.writeUTF(message)

        server.sendPluginMessage(identifier, out.toByteArray())
        return this
    }

    fun send(message: String, player: Player): ProxyMessage {
        val out = ByteStreams.newDataOutput()

        out.writeUTF(messageType)
        out.writeUTF(message)

        player.sendPluginMessage(identifier, out.toByteArray())
        return this
    }

    @Subscribe
    fun onEvent(event: PluginMessageEvent, message: ProxyMessage = this) {
        if (event.identifier != this.identifier)
            onEvent(event, message)
    }
}