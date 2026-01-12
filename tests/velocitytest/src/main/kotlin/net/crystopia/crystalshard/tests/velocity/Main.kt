package net.crystopia.crystalshard.tests.velocity

import com.google.inject.Inject
import com.velocitypowered.api.event.Continuation
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.player.PlayerChatEvent
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent
import com.velocitypowered.api.event.player.PlayerResourcePackStatusEvent
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyPingEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier
import com.velocitypowered.api.proxy.player.ResourcePackInfo
import net.crystopia.crystalshard.common.extension.text
import net.crystopia.crystalshard.velocity.extensions.motd
import net.crystopia.crystalshard.velocity.messaging.ProxyMessage
import net.kyori.adventure.text.Component
import org.slf4j.Logger
import java.util.*


@Plugin(
    id = "crystalshardvelocity", name = "crystalshardvelocity", version = "no_prod",
    url = "https://crystopia.net", description = "crystalshardvelocity", authors = ["xyzjesper"]
)

class Main {

    val uuid = UUID.fromString("6782dfa0-112c-4ff1-b24f-9e34c36b8033")

    private var server: ProxyServer? = null
    private var logger: Logger? = null

    var PACK_INFO: ResourcePackInfo? = null

    @Inject
    constructor(server: ProxyServer, logger: Logger) {
        this.server = server
        this.logger = logger
    }

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent) {

        PACK_INFO =
            server!!.createResourcePackBuilder("https://download.mc-packs.net/pack/1c5abf47dcc64c7733c572d24eeeab9174a761f0.zip")
                .setId(uuid)
                .setShouldForce(true)
                .build()

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
    fun onPlayerConnect(event: PlayerChatEvent) {
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

    private val joinContinuations =
        mutableMapOf<UUID, Continuation>()

    @Subscribe
    fun on(event: ProxyPingEvent) {
        event.motd {
            icon("https://cdn.xyzhub.link/u/wPrxHx.png")
            version(Component.text().text("<red>Welcome...</red>").build())
            description(
                Component.text()
                    .text("<rainbow>Ohh this is a very cool</rainbow>\n<gray>MOTD from CrystalShard...</gray>")
                    .build()
            )
            hover(
                Component.text().text("<color:#fd8aff>MOTD from CrystalShard...</color>")
                    .build()
            )
            build()
        }
    }

    @Subscribe
    fun onPlayerPack(event: PlayerResourcePackStatusEvent) {
        /*
        val player = event.player
        val continuation = joinContinuations[player.uniqueId] ?: return

        when (event.status) {
            PlayerResourcePackStatusEvent.Status.SUCCESSFUL -> {
                joinContinuations.remove(player.uniqueId)
                continuation.resume()
            }

            PlayerResourcePackStatusEvent.Status.DECLINED,
            PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD -> {
                joinContinuations.remove(player.uniqueId)
                player.disconnect(
                    Component.text("Resource Pack ist erforderlich!")
                )
            }

            else -> {}
        }
         */
    }


    @Subscribe(priority = 100)
    fun onModBlock(event: PlayerChooseInitialServerEvent) {
        /*
         val player = event.player
         joinContinuations[player.uniqueId] = continuation
         player.sendResourcePacks(PACK_INFO!!)
         */
    }

}