package net.crystopia.crystalshard.tests.velocity

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PostLoginEvent
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier
import com.velocitypowered.api.proxy.server.ServerInfo
import net.crystopia.crystalshard.velocity.messaging.ProxyMessage
import org.slf4j.Logger
import java.net.InetSocketAddress


@Plugin(
    id = "crystalshardvelocity", name = "crystalshardvelocity", version = "no_prod",
    url = "https://crystopia.net", description = "crystalshardvelocity", authors = ["xyzjesper"]
)

class Main {

    private var server: ProxyServer? = null
    private var logger: Logger? = null

    @Inject
    constructor(server: ProxyServer, logger: Logger) {
        this.server = server
        this.logger = logger
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {

        ProxyMessage(
            server = server!!,
            identifier = MinecraftChannelIdentifier.create("testy", "testy"),
            messageType = "testy"
        ) { message ->

        }.register().send(
            "Hallo schöner server auf dem der player ist...",
            server!!.getServer("lobby").get()
        )
    }

    @Subscribe
    fun onPlayerConnect(event: PostLoginEvent) {
        println("Cool")
        ProxyMessage(
            server = server!!,
            identifier = MinecraftChannelIdentifier.create("testy", "testy"),
            messageType = "testy"
        ) { message ->

        }.register().send(
            "Hallo schöner server auf dem der player ist...",
            server!!.getServer("lobby").get()
        )


    }

}